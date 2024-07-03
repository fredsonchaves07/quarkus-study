package github.fredsonchaves07.domain.forum.repositories;

import github.fredsonchaves07.core.pagination.Pagination;
import github.fredsonchaves07.core.repository.Repository;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;

import java.util.List;

public interface AnswersRepository extends Repository<AnswerID, Answer> {

//    List<Question> findManyRecent(Pagination pagination);

    List<Answer> findManyByQuestionId(Pagination pagination, QuestionID questionID);
}
