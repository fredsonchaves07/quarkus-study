package github.fredsonchaves07.db.repositories.forum;

import github.fredsonchaves07.db.DB;
import github.fredsonchaves07.db.MemoryDB;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;

import java.util.List;
import java.util.Optional;

public class FakeAnswersRepository implements AnswersRepository {

    private final DB<Answer> db;

    private FakeAnswersRepository() {
        db = MemoryDB.createDB();
    }

    @Override
    public AnswerID getId(Answer entity) {
        return null;
    }

    @Override
    public void create(Answer answer) {
        db.push(answer);
    }

    @Override
    public Optional<Answer> findById(AnswerID id) {
        return Optional.empty();
    }

    @Override
    public List<Answer> findAll() {
        return List.of();
    }

    @Override
    public void update(Answer entity) {

    }

    @Override
    public void delete(Answer entity) {

    }

    public static FakeAnswersRepository createRepository() {
        return new FakeAnswersRepository();
    }
}
