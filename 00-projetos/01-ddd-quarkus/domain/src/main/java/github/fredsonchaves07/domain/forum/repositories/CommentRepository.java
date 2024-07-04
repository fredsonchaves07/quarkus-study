package github.fredsonchaves07.domain.forum.repositories;

import github.fredsonchaves07.core.repository.Repository;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;

public interface CommentRepository extends Repository<CommentID, Comment> {

}
