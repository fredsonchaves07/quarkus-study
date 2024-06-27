package github.fredsonchaves07.domain.forum.usecases.choosequestionbestanswer;

import github.fredsonchaves07.core.usecases.UseCase;
import github.fredsonchaves07.domain.forum.entities.answer.Answer;
import github.fredsonchaves07.domain.forum.entities.answer.AnswerID;
import github.fredsonchaves07.domain.forum.entities.author.AuthorID;
import github.fredsonchaves07.domain.forum.entities.question.Question;
import github.fredsonchaves07.domain.forum.entities.question.QuestionID;
import github.fredsonchaves07.domain.forum.repositories.AnswersRepository;
import github.fredsonchaves07.domain.forum.repositories.QuestionRepository;
import github.fredsonchaves07.domain.forum.usecases.answerquestion.AnswerQuestionInput;
import github.fredsonchaves07.domain.forum.usecases.answerquestion.AnswerQuestionOutput;

import java.util.Objects;

public class ChooseQuestionBestAnswerUseCase implements UseCase<ChooseQuestionBestAnswerInput, ChooseQuestionBestAnswerOutput> {

    private final QuestionRepository questionRepository;

    private final AnswersRepository answersRepository;

    public ChooseQuestionBestAnswerUseCase(
            QuestionRepository questionRepository, AnswersRepository answersRepository
    ) {
        this.questionRepository = Objects.requireNonNull(questionRepository);
        this.answersRepository = Objects.requireNonNull(answersRepository);
    }

    public ChooseQuestionBestAnswerOutput execute(ChooseQuestionBestAnswerInput input) {
        AnswerID answerID = new AnswerID(input.anwerId());
        Answer answer = answersRepository
                .findById(new AnswerID(input.anwerId()))
                .orElseThrow(() -> new Error("Answer not found"));
        Question question = questionRepository
                .findById(answer.questionId())
                .orElseThrow(() -> new Error("Question not found"));
        if (!input.authorId().equals(question.authorId().toString()))
            throw new Error("Not Allowed");
        question.updateBestAnwserId(answerID);
        questionRepository.update(question);
        return new ChooseQuestionBestAnswerOutput(
                question.id(), question.authorId().toString(), question.title(), question.content()
        );
    }
}
