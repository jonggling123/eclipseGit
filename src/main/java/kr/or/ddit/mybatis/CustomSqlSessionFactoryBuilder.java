package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sessionFactory;
	static {
		String resource = "kr/or/ddit/mybatis/mybatis-config.xml";
		try(
			Reader reader = Resources.getResourceAsReader(resource);
		){
			sessionFactory = 
					new SqlSessionFactoryBuilder().build(reader);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}
}












