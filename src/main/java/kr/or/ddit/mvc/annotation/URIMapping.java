package kr.or.ddit.mvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 핸들러 객체가 가지고 있는 메서드 중 실제 핸들러 메서드에 사용할 어노테이션
 * 이 핸들러가 처리할 수 있는 요청에 대한 정보 설정. (어떤 주소를 내가 처리할 수 있다.)
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface URIMapping {
	public String value(); //요청 uri
	public HttpMethod method() default HttpMethod.GET;
}
