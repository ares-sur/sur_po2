package org.ares.app.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface TestMapper {

	@SelectProvider(type=TestSQL.class,method="query_rr_sql")
	public List<Map<String,String>> queryRR(Map<String,String> param);
	
}
