package github.fredsonchaves07.db.repositories.forum;

import github.fredsonchaves07.db.DB;
import github.fredsonchaves07.db.MemoryDB;
import github.fredsonchaves07.domain.forum.entities.comment.Comment;
import github.fredsonchaves07.domain.forum.entities.comment.CommentID;
import github.fredsonchaves07.domain.forum.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {

    private final DB<Comment> db;

    private FakeCommentRepository() {
        db = MemoryDB.createDB();
    }

    public static FakeCommentRepository createRepository() {
        return new FakeCommentRepository();
    }

    @Override
    public CommentID getId(Comment entity) {
        return null;
    }

    @Override
    public void create(Comment entity) {
        db.push(entity);
    }

    @Override
    public Optional<Comment> findById(CommentID id) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
        return List.of();
    }

    @Override
    public void update(Comment entity) {

    }

    @Override
    public void delete(Comment entity) {

    }
}
