package github.fredsonchaves07.domain.forum.repositories;

import github.fredsonchaves07.core.repository.Repository;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;

public interface AnswersRepository extends Repository<AnswerID, Answer> {

}
