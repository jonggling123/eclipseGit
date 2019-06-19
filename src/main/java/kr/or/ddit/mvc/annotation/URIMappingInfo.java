package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/**
 * 웹 상에서 발생한 요청과 프론트 뒷단에서 동작하는 커맨드 핸들러에 대한
 * 매핑 정보를 가진 객체, 어떤 요청을 누가 처리할 수 있다는 정보.
 *
 */
public class URIMappingInfo {
	private URIMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
	
	public URIMappingInfo(URIMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}
	
	public URIMappingCondition getMappingCondition() {
		return mappingCondition;
	}
	public void setMappingCondition(URIMappingCondition mappingCondition) {
		this.mappingCondition = mappingCondition;
	}
	public Object getCommandHandler() {
		return commandHandler;
	}
	public void setCommandHandler(Object commandHandler) {
		this.commandHandler = commandHandler;
	}
	public Method getHandlerMethod() {
		return handlerMethod;
	}
	public void setHandlerMethod(Method handlerMethod) {
		this.handlerMethod = handlerMethod;
	}

	@Override
	public String toString() {
		return "URIMappingInfo [commandHandler=" + commandHandler.getClass().getName() + ", handlerMethod=" + handlerMethod.getName() + "]";
	}
	
	
}
