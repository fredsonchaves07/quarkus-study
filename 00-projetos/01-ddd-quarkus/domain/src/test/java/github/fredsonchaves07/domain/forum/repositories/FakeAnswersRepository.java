package github.fredsonchaves07.domain.forum.repositories;

import github.fredsonchaves07.domain.forum.entities.answer.Answer;

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
