package com.projectx.data.controller.ivr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.data.repository.ivr.QuestionPossibleAnswersSelectedAnswerRepository;

@RestController
@RequestMapping(value="/ivr")
public class QuestionPossibleAnswersSelectedAnswerController {

	@Autowired
	QuestionPossibleAnswersSelectedAnswerRepository questionPossibleAnswersSelectedAnswerRepository;
	
	@RequestMapping(value="/saveQuestion",method=RequestMethod.POST)
	public ResponseEntity<QuestionPossibleAnswersSelectedAnswer> save(@RequestBody QuestionPossibleAnswersSelectedAnswer entity)
	{
		ResponseEntity<QuestionPossibleAnswersSelectedAnswer> result=null;
		
		QuestionPossibleAnswersSelectedAnswer savedEntity=questionPossibleAnswersSelectedAnswerRepository.save(entity);
		
		result=new ResponseEntity<QuestionPossibleAnswersSelectedAnswer>(savedEntity, HttpStatus.CREATED);
		
		return result;
	}
	
	@RequestMapping(value="/getAllQuestions",method=RequestMethod.GET)
	public ResponseEntity<List<QuestionPossibleAnswersSelectedAnswer>> getAll()
	{
		List<QuestionPossibleAnswersSelectedAnswer> list=(List<QuestionPossibleAnswersSelectedAnswer>) questionPossibleAnswersSelectedAnswerRepository.findAll();
		
		return new ResponseEntity<List<QuestionPossibleAnswersSelectedAnswer>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getQuestionById/{questionId}",method=RequestMethod.GET)
	public ResponseEntity<QuestionPossibleAnswersSelectedAnswer> getQuestionById(@PathVariable Long questionId)
	{
		ResponseEntity<QuestionPossibleAnswersSelectedAnswer> result=null;
		
		QuestionPossibleAnswersSelectedAnswer fetchedEntity=questionPossibleAnswersSelectedAnswerRepository
				.findOne(questionId);
		
		if(fetchedEntity!=null)
			result=new ResponseEntity<QuestionPossibleAnswersSelectedAnswer>(fetchedEntity, HttpStatus.FOUND);
		else
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return result;
	}
	
}
