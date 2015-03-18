package com.projectx.data.domain.ivr;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name="IVRQUESTIONS")
public class QuestionPossibleAnswersSelectedAnswer {
	
	@Column(name="QUESTIONID")
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long questionId;
	
	@Column(name="QUESTION")
	private String question;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="QUESTIONPOSSIBLEANSWER",joinColumns=@JoinColumn(name="QUESTIONID"))
	private List<String> possibleAnswer;
	
	@Column(name="SELECTEDANSWER")
	private Integer selectedAnswer;

	public QuestionPossibleAnswersSelectedAnswer() {

	}

	
	
	public QuestionPossibleAnswersSelectedAnswer(String question,
			List<String> possibleAnswer, Integer selectedAnswer) {
		super();
		this.question = question;
		this.possibleAnswer = possibleAnswer;
		this.selectedAnswer = selectedAnswer;
	}



	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getPossibleAnswer() {
		return possibleAnswer;
	}

	public void setPossibleAnswer(List<String> possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}



	public Integer getSelectedAnswer() {
		return selectedAnswer;
	}



	public void setSelectedAnswer(Integer selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}



	public Long getQuestionId() {
		return questionId;
	}



	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}



	@Override
	public String toString() {
		return "QuestionPossibleAnswerSelectedAnswerDTO [question=" + question
				+ ", possibleAnswer=" + possibleAnswer + ", selectedAnswer="
				+ selectedAnswer + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((possibleAnswer == null) ? 0 : possibleAnswer.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result
				+ ((selectedAnswer == null) ? 0 : selectedAnswer.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionPossibleAnswersSelectedAnswer other = (QuestionPossibleAnswersSelectedAnswer) obj;
		if (possibleAnswer == null) {
			if (other.possibleAnswer != null)
				return false;
		} else if (!possibleAnswer.equals(other.possibleAnswer))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (selectedAnswer == null) {
			if (other.selectedAnswer != null)
				return false;
		} else if (!selectedAnswer.equals(other.selectedAnswer))
			return false;
		return true;
	}
	


}
