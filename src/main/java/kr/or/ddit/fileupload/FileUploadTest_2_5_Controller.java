package kr.or.ddit.fileupload;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class FileUploadTest_2_5_Controller {
	@URIMapping(value="/fileUpload2_5.do", method=HttpMethod.POST)
	public String uploadTest(HttpServletRequest req, HttpServletResponse resp) {
//		commons-fileupload 를 이용한 파일 업로드 단계
//		1. 파일의 임시 저장소 객체(분할되서 날아오는 데이터들을 합치고 관리할 공간) 생성
		File repository = new File("d:/temp");
		DiskFileItemFactory itemFactory = new DiskFileItemFactory(100*1024, repository);
//		2. 1번의 저장소 객체를 넘겨서 파일 업로드 핸들러 객체 생성
		ServletFileUpload uploadHandler = new ServletFileUpload(itemFactory);
//		3. 핸들러 객체를 통해 multipart request를 파싱함.
//		4. 한 파트 당 FileItem 객체가 하나씩 생성
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>(); //db역할을 할 map
		File saveFolder = new File("d:/savefolder"); //파일을 저장할 폴더
		try {
			List<FileItem> itemList = uploadHandler.parseRequest(req);
//		5. FileItem 들을 대상으로 반복
			for(FileItem item : itemList) {
//			FileItem의 특성을 확인하고, 문자 데이터인지 파일 데이터인지에 따라 별도 처리
				String inputTagName = item.getFieldName();
				if(item.isFormField()) { //파일이면 false
					String paramValue = item.getString("UTF-8");
					dataMap.put(inputTagName, paramValue);
				}else {
					String savename = UUID.randomUUID().toString();
					File saveFile = new File(saveFolder, savename);
					try(
						InputStream is = item.getInputStream();
					){
						FileUtils.copyInputStreamToFile(is, saveFile);
						Map<String, Object> fileMetaData = new LinkedHashMap<String, Object>();
						fileMetaData.put("filename", item.getName());
						fileMetaData.put("filesize", item.getSize());
						fileMetaData.put("filemime", item.getContentType());
						fileMetaData.put("save", item.getContentType());
						dataMap.put(inputTagName, fileMetaData);
						item.delete();
					}
				}
			}
			req.setAttribute("dataMap", dataMap);
			return "redirect:/14/fileUploadForm.jsp";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
