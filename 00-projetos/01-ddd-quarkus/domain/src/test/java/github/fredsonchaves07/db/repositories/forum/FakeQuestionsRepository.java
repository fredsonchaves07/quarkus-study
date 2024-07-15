package github.fredsonchaves07.db.repositories.forum;

import github.fredsonchaves07.core.pagination.Pagination;
import github.fredsonchaves07.db.DB;
import github.fredsonchaves07.db.MemoryDB;
import github.fredsonchaves07.domain.forum.entities.attachment.QuestionAttachment;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.entities.valueobjects.Slug;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;

import java.util.*;

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

    @Override
    public List<Question> findManyRecent(Pagination pagination) {
        int initialPage = 0;
        int finishPage = count();
        if (pagination.page() > 0) initialPage = pagination.page() - 1;
        if (finishPage > (pagination.page() * 20)) finishPage = (pagination.page() * 20);
        return findAll()
                .stream()
                .sorted(new QuestionRecentComparator())
                .toList()
                .subList(initialPage * 20, finishPage);
    }

    @Override
    public List<QuestionAttachment> findQuestionAttachmentByQuestionId(QuestionID questionID) {
        return findAll().stream()
                .filter(question -> question.id().equals(questionID.value()))
                .findFirst()
                .orElseThrow()
                .attachments()
                .currentItems();
    }

    private class QuestionRecentComparator implements Comparator<Question> {

        @Override
        public int compare(Question question1, Question question2) {
            return question1.createdAt().compareTo(question2.createdAt()) * -1;
        }
    }
}
