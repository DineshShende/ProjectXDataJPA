package com.projectx.data.repository.ivr;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.ivr.QuestionPossibleAnswersSelectedAnswer;

@Repository
public interface QuestionPossibleAnswersSelectedAnswerRepository extends CrudRepository<QuestionPossibleAnswersSelectedAnswer,Long> {

}
