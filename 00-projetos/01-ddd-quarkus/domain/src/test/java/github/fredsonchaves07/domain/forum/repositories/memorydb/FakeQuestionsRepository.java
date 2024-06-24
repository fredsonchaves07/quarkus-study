package github.fredsonchaves07.domain.forum.repositories.memorydb;

import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

public class FakeQuestionsRepository implements QuestionRepository {

    private FakeQuestionsRepository() {

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
