package next.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import next.model.Question;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;

public class QuestionDaoTest {
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	public void crud() throws Exception {
		
		Question expected = new Question("자바지기", "title", "contents");
		QuestionDao dut = new QuestionDao();
		dut.insert(expected);
		
		List<Question> questions = dut.findAll();
		assertTrue(questions.size() > 0);
	}
	
	@Test
	public void comment() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
		Long questionId = (long) 1;
		QuestionDao dut = new QuestionDao();
		Question before = dut.findById(questionId);
		int tmp = before.getCountOfComment();
		dut.increaseCountOfComment(questionId);
		Question after = dut.findById(questionId);
		int expected = after.getCountOfComment();
		assertTrue(expected > tmp);
	}
}
