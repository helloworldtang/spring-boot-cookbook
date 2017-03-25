package com.tangcheng.flyway;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleFlywayApplicationTests {

	@Autowired
    private JdbcTemplate template;

	@Test
	public void testDefaultSettings() throws Exception {
		assertThat(this.template.queryForObject("SELECT COUNT(*) from city",
				Integer.class)).isEqualTo(3);
	}

}