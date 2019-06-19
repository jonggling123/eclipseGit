package kr.or.ddit.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.MemberVO;

public class CustomServletContextListener implements ServletContextListener{
   private static Logger logger = 
         LoggerFactory.getLogger(CustomServletContextListener.class);
   
   @Override
   public void contextInitialized(ServletContextEvent sce) {
      ServletContext application = sce.getServletContext();
      application.setAttribute("userCount", new Integer(0));
      application.setAttribute(Constants.USERLISTATTRNAME, new HashSet<MemberVO>());
      //primative data와 object data가 다름에도 '0'이 가능한 이유는 auto-boxing 덕분
      Properties prop = new Properties();
      InputStream is = getClass().getResourceAsStream("/kr/or/ddit/appInfo.properties");
//      <!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
//      이걸 사용해서 properties 파일을 xml 파일로 직접 읽어올 수도 있음
      try {
         prop.load(is);
         application.setAttribute("appInfo", prop);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      logger.info("{} 컨텍스트 초기화", application.getContextPath());
   }

   @Override
   public void contextDestroyed(ServletContextEvent sce) {
      ServletContext application = sce.getServletContext();
      logger.info("{} 컨텍스트 소멸", application.getContextPath());
   }

}