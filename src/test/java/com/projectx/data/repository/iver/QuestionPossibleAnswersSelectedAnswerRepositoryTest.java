package com.projectx.data.repository.iver;

import static com.projectx.data.config.Constants.SPRING_PROFILE_ACTIVE_TEST;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.data.repository.ivr.QuestionPossibleAnswersSelectedAnswerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class QuestionPossibleAnswersSelectedAnswerRepositoryTest {

	@Autowired
	QuestionPossibleAnswersSelectedAnswerRepository questionPossibleAnswersSelectedAnswerRepository;
	
	
	@Test
	public void save()
	{
		List<String> possibileAnswer=new ArrayList<String>();
		
		possibileAnswer.add("A");
		possibileAnswer.add("B");
		possibileAnswer.add("C");
		possibileAnswer.add("D");
		
		QuestionPossibleAnswersSelectedAnswer questionPossibleAnswersSelectedAnswer=
				new QuestionPossibleAnswersSelectedAnswer("Hi How R U?", possibileAnswer, "A");
		
		QuestionPossibleAnswersSelectedAnswer savedEntity=questionPossibleAnswersSelectedAnswerRepository
				.save(questionPossibleAnswersSelectedAnswer);
		
		QuestionPossibleAnswersSelectedAnswer fetchedEntity=questionPossibleAnswersSelectedAnswerRepository
				.findOne(savedEntity.getQuestionId());
		
		System.out.println(fetchedEntity);
		
	}
	
	
}
