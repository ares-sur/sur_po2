package org.ares.app.dao.mybatis;

import java.util.Map;

import org.springframework.util.StringUtils;

public class TestSQL {

	public String query_rr_sql(Map<String,String> param) {
		StringBuilder sql=new StringBuilder("select * from s_role_res rr where 1=1 ");
		if(!StringUtils.isEmpty(param.get("roleid")))
			sql.append("and rr.roleid=#{roleid} ");
		if(!StringUtils.isEmpty(param.get("resid")))
			sql.append("and rr.resid=#{resid} ");
		return sql.toString();
	}
	
}
