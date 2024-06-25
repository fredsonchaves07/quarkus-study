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
        return findAll().stream().filter(answer -> answer.id().equals(id.value())).findFirst();
    }

    @Override
    public List<Answer> findAll() {
        return db.listAll();
    }

    @Override
    public void update(Answer entity) {
        Answer answer = findById(new AnswerID(entity.id())).get();
        db.replace(answer, entity);
    }

    @Override
    public void delete(Answer entity) {
        db.delete(entity);
    }

    public static FakeAnswersRepository createRepository() {
        return new FakeAnswersRepository();
    }
}
