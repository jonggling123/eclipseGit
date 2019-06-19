package kr.or.ddit.wrapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

/**
 * Multipart request 의 경우, 비어있는 parameterMap을 대신할
 * 새로운 parameterMap의 생성.
 * 업로드된 파일 데이터를 관리할 맵 생성
 *
 */
public class FileUploadRequestWrapper extends HttpServletRequestWrapper {

	//1. part를 다 찾아내야함
	//2. 문자들을 다 뽑아서 파라미터로 만들어준다
	public FileUploadRequestWrapper(HttpServletRequest request) throws IOException{
		this(request, -1, null);
	}
	
	public FileUploadRequestWrapper(HttpServletRequest request, 
			int sizeThreshold, File repository) throws IOException{
		super(request);
		//parsing 할때 필요한 encoding 잡기
		encoding = request.getCharacterEncoding();
		
		parameterMap = new LinkedHashMap<String, String[]>();
		fileItemMap = new LinkedHashMap<String, List<FileItem>>();
		//경우에 따라 queryString이 존재할 수도 있기 때문에(post요청이 body에만 데이터를 담는건 아니다)
		//그것 또한 챙겨줘야 한다.
		parameterMap.putAll(request.getParameterMap());
		
		parseRequest(request, sizeThreshold, repository);
	}

	//inputType file 이외의 inputTag
	private Map<String, String[]> parameterMap;
	//inputType file 인 inputTag
	private Map<String, List<FileItem>> fileItemMap;
	private String encoding;
	
	private void parseRequest(HttpServletRequest request, int sizeThreshold, File repository) throws IOException {
		//1. 임시 저장소 객체 생성 (데이터 이동이 chunk 단위로 이동하기 때문에)
		DiskFileItemFactory itemFactory = new DiskFileItemFactory();
		//sizeThreshold, repository check
		if(sizeThreshold!=-1) {
			itemFactory.setSizeThreshold(sizeThreshold);
		}
		if(repository!=null) {
			itemFactory.setRepository(repository);
		}
		//uploadHandler 객체 생성
		ServletFileUpload uploadHandler = new ServletFileUpload(itemFactory);
		try {
			List<FileItem> itemList = uploadHandler.parseRequest(request);
			if(itemList==null || itemList.size()==0) {
				return;
			}
			for(FileItem item : itemList) {
				String partName = item.getFieldName();
				//문자 데이터와 파일 데이터 구분
				if(item.isFormField()) {
					//문자 데이터 -> parameterMap 채우기
					String paramValue = item.getString(encoding);
					//하나의 key에 여러 데이터가 오는 경우
					String[] paramValues = parameterMap.get(partName);
					String[] newArray = null;
					if(paramValues==null) { //처음 넣을때
						newArray = new String[1];
					}else { //이미 들어있는 데이터가 있음
						//크기 +1 한 배열에 복사
						newArray = new String[paramValues.length + 1];
						System.arraycopy(paramValues, 0, newArray, 0, paramValues.length);
					}
					//반복중에 map에 존재하는 partName이라면 마지막 방에 값을 채워준다
					newArray[newArray.length - 1] = paramValue;
					//복사한 배열을 새로이 덮어씌움
					parameterMap.put(partName, newArray);
				}else {
					//파일 데이터의 유무 판단
					String filename = item.getName();
					if(StringUtils.isBlank(filename)) {
						continue;
					}
					//파일 데이터 -> fileItemMap 채우기
					List<FileItem> fileList = fileItemMap.get(partName);
					if(fileList==null) {
						fileList = new ArrayList<FileItem>();
						fileItemMap.put(partName, fileList);
					}
					fileList.add(item);
				} //if end
			} //for end
		} catch (FileUploadException e) {
			throw new IOException(e);
		} //try-catch end
	}
	
	//parameterMap 접근 방법 제공
	@Override
	public String getParameter(String name) {
		if(parameterMap.containsKey(name)) {
			return parameterMap.get(name)[0];
		}else {
			return null;
		}
	}
	
	@Override
	public String[] getParameterValues(String name) {
		return parameterMap.get(name);
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		final Iterator<String> paramNames = parameterMap.keySet().iterator();
		return new Enumeration<String>() {

			@Override
			public boolean hasMoreElements() {
				return paramNames.hasNext();
			}

			@Override
			public String nextElement() {
				return paramNames.next();
			}
			
		};
	}
	
	//fileItemMap 접근 방법 제공
	public FileItem getFileItem(String name) {
		List<FileItem> fileList = fileItemMap.get(name);
		FileItem file = null;
		if(fileList!=null && fileList.size()>0) {
			file = fileList.get(0);
		}
		return file;
	}
	
	public List<FileItem> getFileItems(String name) {
		return fileItemMap.get(name);
	}
	
	public Enumeration<String> getFileItemNames() {
		final Iterator<String> fileItemNames = fileItemMap.keySet().iterator();
		return new Enumeration<String>() {

			@Override
			public boolean hasMoreElements() {
				return fileItemNames.hasNext();
			}

			@Override
			public String nextElement() {
				return fileItemNames.next();
			}
		};
	}
	
	//임시 파일 삭제 방법 제공
	public void deleteTempFiles() {
		for(Entry<String, List<FileItem>> entry : fileItemMap.entrySet()) {
			List<FileItem> tempList = entry.getValue();
			for(FileItem temp : tempList) {
				temp.delete();
			}
		}
	}
}
