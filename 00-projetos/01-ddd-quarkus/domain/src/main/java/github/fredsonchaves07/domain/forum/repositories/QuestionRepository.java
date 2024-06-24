package github.fredsonchaves07.domain.forum.repositories;

import github.fredsonchaves07.core.repository.Repository;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;

public interface QuestionRepository extends Repository<QuestionID, Question> {
}
