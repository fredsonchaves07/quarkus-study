package github.fredsonchaves07.db.repositories.forum;

import github.fredsonchaves07.db.DB;
import github.fredsonchaves07.db.MemoryDB;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
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
        return Optional.empty();
    }

    @Override
    public List<Question> findAll() {
        return List.of();
    }

    @Override
    public void update(Question entity) {

    }

    @Override
    public void delete(Question entity) {

    }
}
