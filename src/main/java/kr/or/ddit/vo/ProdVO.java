package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Data Mapper 를 이용한 테이블 조인 
 * 1. 메인 테이블을 기준으로 조인 대상 테이블 간의 관계 파악
 * 		PROD : BUYER -> 1 : 1
 *      PROD : MEMBER -> 1 : N
 * 2. 1번의 관계성을 Domain layer 의 VO 들에 반영
 * 		ProdVO has A BuyerVO
 * 		ProdVO has Many MemberVO
 * 3. resultMap 을 이용한 바인드 설정
 * 		has a 관계 - association 
 * 		has many 관계  - collection 으로 매핑 설정.
 *    1) nested map : 한번의 조인 구문으로 조회
 *    2) nested select : 각 테이블로부터 조회 구문을 분리 설정.
 *    		*** 1+N 구조의 문제가 발생할 수 있으므로 제한적 사용.
 *
 */
@Data
@EqualsAndHashCode(of="prod_id")
public class ProdVO implements Serializable{
	private Long rnum;
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String lprod_nm;
	private String prod_buyer;
	private String buyer_name;
	private Integer prod_cost;
	private Integer prod_price;
	private Integer prod_sale;
	private String prod_outline;
	private String prod_detail;
	private String prod_img;
	private Integer prod_totalstock;
	private String prod_insdate;
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;
	
	private BuyerVO buyer;
	private List<MemberVO> memberList;
}








