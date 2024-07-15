package github.fredsonchaves07.domain.forum.repositories;

import github.fredsonchaves07.core.pagination.Pagination;
import github.fredsonchaves07.core.repository.Repository;
import github.fredsonchaves07.domain.forum.entities.attachment.QuestionAttachment;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.entities.valueobjects.Slug;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends Repository<QuestionID, Question> {

    Optional<Question> findBySlug(Slug slug);

    List<Question> findManyRecent(Pagination pagination);

    List<QuestionAttachment> findQuestionAttachmentByQuestionId(QuestionID questionID);
}
