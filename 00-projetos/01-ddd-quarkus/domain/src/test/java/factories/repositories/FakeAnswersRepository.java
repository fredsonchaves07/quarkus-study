package factories.repositories;

import entities.Answer;
import repositories.AnswersRepository;

public class FakeAnswersRepository implements AnswersRepository {

    private FakeAnswersRepository() {

    }

    @Override
    public void create(Answer answer) {

    }

    public static FakeAnswersRepository createRepository() {
        return new FakeAnswersRepository();
    }
}
