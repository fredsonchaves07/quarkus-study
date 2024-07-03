package github.fredsonchaves07.db.repositories.forum;

import github.fredsonchaves07.core.pagination.Pagination;
import github.fredsonchaves07.db.DB;
import github.fredsonchaves07.db.MemoryDB;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
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

    @Override
    public List<Answer> findManyByQuestionId(Pagination pagination, QuestionID questionID) {
        List<Answer> answers = findAll()
                .stream()
                .filter(answer -> answer.questionId().equals(questionID))
                .toList();
        int initialPage = 0;
        int finishPage = count();
        if (pagination.page() > 0) initialPage = pagination.page() - 1;
        if (finishPage > (pagination.page() * 20)) finishPage = (pagination.page() * 20);
        return answers.subList(initialPage * 20, finishPage);
    }


//    @Override
//    public List<Question> findManyRecent(Pagination pagination) {
//        int initialPage = 0;
//        int finishPage = count();
//        if (pagination.page() > 0) initialPage = pagination.page() - 1;
//        if (finishPage > (pagination.page() * 20)) finishPage = (pagination.page() * 20);
//        return findAll()
//                .stream()
//                .sorted(new FakeAnswersRepository.QuestionRecentComparator())
//                .toList()
//                .subList(initialPage * 20, finishPage);
//    }
//
//    private class QuestionRecentComparator implements Comparator<Question> {
//
//        @Override
//        public int compare(Question question1, Question question2) {
//            return question1.createdAt().compareTo(question2.createdAt()) * -1;
//        }
//    }
}
