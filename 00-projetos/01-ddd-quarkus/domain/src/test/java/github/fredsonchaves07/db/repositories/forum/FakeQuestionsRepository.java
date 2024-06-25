package github.fredsonchaves07.db.repositories.forum;

import github.fredsonchaves07.db.DB;
import github.fredsonchaves07.db.MemoryDB;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.entities.valueobjects.Slug;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

public class FakeQuestionsRepository implements QuestionRepository {

    private final DB<Question> db;

    private FakeQuestionsRepository() {
        db = MemoryDB.createDB();
    }

    public static FakeQuestionsRepository createRepository() {
        return new FakeQuestionsRepository();
    }

    @Override
    public QuestionID getId(Question entity) {
        return null;
    }

    @Override
    public void create(Question entity) {
        db.push(entity);
    }

    @Override
    public Optional<Question> findById(QuestionID id) {
        return findAll().stream().filter(question -> question.id().equals(id.value())).findFirst();
    }

    @Override
    public List<Question> findAll() {
        return db.listAll();
    }

    @Override
    public void update(Question entity) {

    }

    @Override
    public void delete(Question entity) {
        db.delete(entity);
    }

    @Override
    public Optional<Question> findBySlug(Slug slug) {
        return findAll().stream().filter(question -> question.slug().equals(slug)).findFirst();
    }
}
