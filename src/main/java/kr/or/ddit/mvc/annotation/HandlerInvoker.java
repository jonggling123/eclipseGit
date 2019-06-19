package kr.or.ddit.mvc.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInvoker implements IHandlerInvoker {
	@Override
	public String invokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) {
		Object handler = mappingInfo.getCommandHandler(); //누가
		Method handlerMethod = mappingInfo.getHandlerMethod(); //어떤 메서드
		
		try {
			//handlerMethod의 규칙
			//0. @URIMapper
			//1. paramether : req, resp
			//2. returnType : String
			Object retValue = handlerMethod.invoke(handler, req, resp);
			return (String) retValue;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e); //개발자 잘못 (500)
		}
	}

}
