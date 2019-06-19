package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.xml.ws.http.HTTPBinding;

import org.apache.ibatis.type.Alias;

import kr.or.ddit.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원관리를 위해 MEMBER 테이블을 대상으로 한 Domain Layer
 * 회원(MEMBER) : 상품(PROD) - 1:N -> has Many
 *
 * 1. 회원의 기본정보만 조회
 * 2. 회원의 기본 정보 + 구매 상품 목록
 * 3. 회원의 기본 정보 + 구매 이력 정보 + 각상품명, 거래처명, 상품의 판매가와 구매수량을 동시에 조회
 * 		MEMBER	CART  PROD
 * 		 	1  :  N  : N
 */
@Alias("memberVO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of={"mem_id", "mem_regno1", "mem_regno2"})
public class MemberVO implements Serializable, HttpSessionBindingListener{
	
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}
	
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_regno1;
	private String mem_regno2;
	private String mem_bir;
	private String mem_zip;
	private String mem_add1;
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
	//파일 저장을 위한 변수(테이블에 컬럼이 추가됨)
	private byte[] mem_img;
	//role 변수
	private String mem_auth;
	
//	private List<ProdVO> prodList;
	private List<CartVO> cartList;

	public String getTestStr() {
		return "테스트 스트링";
	}
	
	public String getMem_imgBase64() {
		String base64Str = null;
		if(mem_img!=null) {
			base64Str = Base64.getEncoder().encodeToString(mem_img);
		}
		return base64Str;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			ServletContext application = event.getSession().getServletContext();
			Set<MemberVO> userList = (Set<MemberVO>) application.getAttribute(Constants.USERLISTATTRNAME);
			userList.add(this);
		}
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			ServletContext application = event.getSession().getServletContext();
			Set<MemberVO> userList = (Set<MemberVO>) application.getAttribute(Constants.USERLISTATTRNAME);
			userList.remove(this);
		}
	}
}












