package kr.or.ddit.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class FileUploadTest_3_0_Controller {
	@URIMapping(value="/fileUpload.do", method=HttpMethod.POST)
	public String uploadTest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String uploader = req.getParameter("uploader");
		//parameter로 꺼내면 파일명만 넘어온다
//		String uploadFileName = req.getParameter("uploadFile");
		String uploadFileName = null;
//		Part uploaderPart = req.getPart("uploader");
		Part uploadFile = req.getPart("uploadFile");
//		File saveFolder = new File("d:/savefolder");
		String fileSystemPath = req.getServletContext().getRealPath("/savefolder");
		File saveFolder = new File(fileSystemPath);
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
//		"KakaoTalk_20190401_152922151.jpg"
		String header = uploadFile.getHeader("Content-Disposition");
		int index = header.indexOf("filename");
		header = header.substring(index);
		String filename = header.split("=")[1].replaceAll("\"", "");
		String savename = UUID.randomUUID().toString();
		File saveFile = new File(saveFolder, savename);
		//파일 이름 중복 시 문제점 : 같은 이름의 파일이 존재하면 이전의 데이터를 덮어씌움
		//해킹의 위험성 : 
		try(
			InputStream is = uploadFile.getInputStream();
		){
			FileUtils.copyInputStreamToFile(is, saveFile);
		}
		
		System.out.printf("업로더 : %s, 업로드파일명 : %s, 업로드 사이즈 : %d\n"
				, uploader, filename, uploadFile.getSize());
		return "redirect:/14/fileUploadForm.jsp";
	}
}
