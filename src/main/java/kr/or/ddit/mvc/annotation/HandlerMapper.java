package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.utils.ReflectionUtils;

public class HandlerMapper implements IHandlerMapper {
	//핸들러를 찾아내는 방법
	//1. 주소, 2. 메서드 => URIMappingCondition
	private Map<URIMappingCondition, URIMappingInfo> handlerMap;
	private static Logger logger = LoggerFactory.getLogger(HandlerMapper.class);
	
	public HandlerMapper(String... basePackages) {
		//0. handlerMap 객체 생성
		handlerMap = new LinkedHashMap<URIMappingCondition, URIMappingInfo>();
		//1. basePackage 들을 대상으로 내부의 클래스들을 수집 (@CommandHandler)
		List<Class<?>> classList = new ArrayList<Class<?>>();
		for(String basePackage : basePackages) {
			classList.addAll(ReflectionUtils
					.getClassesAtBasePackageWithAnnotation(basePackage, CommandHandler.class));
		}
		//2. 가져온 객체 안의 메서드 수집 (@URIMapping)
		for(Class<?> clz : classList) {
			List<Method> methodList = ReflectionUtils.getMethodsAtClassWithAnnotation(clz, URIMapping.class
					, String.class, HttpServletRequest.class, HttpServletResponse.class);
			//3. 확보한 데이터로 handlerMap의 key(URIMappingCondition), value(URIMappingInfo) 객체 생성
			addHandlerMap(clz, methodList);
		}
	}
	
	private void addHandlerMap(Class<?> clz, List<Method> methodList) {
		Object commandHandler;
		try {
			commandHandler = clz.newInstance();
			for(Method handlerMethod : methodList) {
				URIMapping mapping = handlerMethod.getAnnotation(URIMapping.class);
				//3-1. URIMappingCondition 객체 생성
				URIMappingCondition condition = new URIMappingCondition(mapping.value()
													, mapping.method());
				//3-2. URIMappingInfo 객체 생성
				URIMappingInfo mappingInfo = new URIMappingInfo(condition, commandHandler
													, handlerMethod);
				//확인을 위한 logger 사용
				logger.info("{} 요청에 대한 핸들러 정보 : {}", condition.toString()
								, mappingInfo.toString());
				//3-3. handlerMap에 담기
				handlerMap.put(condition, mappingInfo);
			}
		} catch (InstantiationException | IllegalAccessException e) { //예외 : 생성자가 private 같은 경우 (서버 잘못)
			throw new RuntimeException(e);
		} 
	}
	
	@Override
	public URIMappingInfo findCommandHandler(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String[] tmp = uri.split(";");
		uri = tmp[0];
		uri = uri.substring(req.getContextPath().length());
		
		String method = req.getMethod();
		HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());
		
		URIMappingCondition condition = new URIMappingCondition(uri, httpMethod);
		return handlerMap.get(condition);
	}

}
