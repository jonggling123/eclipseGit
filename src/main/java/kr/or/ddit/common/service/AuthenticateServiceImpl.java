package kr.or.ddit.common.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService {
	IMemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public Object authenticate(MemberVO member) {
		MemberVO savedMember = memberDAO.selectMember(member.getMem_id());
		Object result = null;
		if(savedMember==null || "Y".equals(savedMember.getMem_delete())) {
			result = ServiceResult.NOTEXIST;
		}else {
			if(savedMember.getMem_pass().equals(member.getMem_pass())) {
//				try {
//					BeanUtils.copyProperties(member, savedMember);
//				} catch (IllegalAccessException | InvocationTargetException e) {
//					throw new RuntimeException(e);
//				}
//				result = ServiceResult.OK;
				result = savedMember;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}
		return result;
	}

}
