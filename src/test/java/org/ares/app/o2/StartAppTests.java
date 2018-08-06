package org.ares.app.o2;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.ares.app.dao.mybatis.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StartAppTests {

	@Test
	public void contextLoads() {
		PageHelper.startPage(1, 2);
		Map<String,String> param=new HashMap<>();
		tm.queryRR(param).stream().forEach(System.out::println);
		System.out.println("********************************************");
		param.put("roleid", "0001");
		tm.queryRR(param).stream().forEach(System.out::println);
		System.out.println("********************************************");
		param.put("resid", "0001");
		tm.queryRR(param).stream().forEach(System.out::println);
		System.out.println("********************************************");
	}
	
	@Resource TestMapper tm;

}
