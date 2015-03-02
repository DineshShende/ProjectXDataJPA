package com.projectx.data.domain.ivr;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuestionPossibleAnswersSelectedAnswer is a Querydsl query type for QuestionPossibleAnswersSelectedAnswer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuestionPossibleAnswersSelectedAnswer extends EntityPathBase<QuestionPossibleAnswersSelectedAnswer> {

    private static final long serialVersionUID = -1617392794L;

    public static final QQuestionPossibleAnswersSelectedAnswer questionPossibleAnswersSelectedAnswer = new QQuestionPossibleAnswersSelectedAnswer("questionPossibleAnswersSelectedAnswer");

    public final ListPath<String, StringPath> possibleAnswer = this.<String, StringPath>createList("possibleAnswer", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath question = createString("question");

    public final NumberPath<Long> questionId = createNumber("questionId", Long.class);

    public final StringPath selectedAnswer = createString("selectedAnswer");

    public QQuestionPossibleAnswersSelectedAnswer(String variable) {
        super(QuestionPossibleAnswersSelectedAnswer.class, forVariable(variable));
    }

    public QQuestionPossibleAnswersSelectedAnswer(Path<? extends QuestionPossibleAnswersSelectedAnswer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionPossibleAnswersSelectedAnswer(PathMetadata<?> metadata) {
        super(QuestionPossibleAnswersSelectedAnswer.class, metadata);
    }

}

