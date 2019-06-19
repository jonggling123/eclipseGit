Front Controller pattern 과 Commend Pattern 을 적용하기 위해
Sbring frameWork 사용 방법.

1. FrontController 를 컨테이너에 등록(web.xml)
	1) basePackages : CommandHandler annotation을 스캔할 패키지들 설정(공백으로 구분)
	2) prefix : view layer의 공통 위치
	3) suffix : view layer의 공통 확장자
2. FrontController 매핑 설정 : 명령들(요청들)을 수집할 수 있는 확장자나 경로 패턴.
	ex) *.do, /*, /member/* ...
3. 각 요청을 처리할 수 있는 CommendHandler 작성 규칙
	1) @CommandHandler marker annotation으로 HandlerMapper에 의해 수집되도록 함.
	2) 실제 handlerMethod에 @URIMapping annotation으로 웹 요청을 받을 수 있는 정보 설정.
		ex) uri / method
	3) handlerMethod의 signiture
		public String methodName(HttpServletRequest, HttpServletResponse)
	4) handlerMethod에서 결정된 logical view name 작성 규칙.
		: forward 이동 시, prefix/suffix 를 제외한 logical view name을 사용.
		: redirect 이동 시, "redirect:" + serverside absolute path