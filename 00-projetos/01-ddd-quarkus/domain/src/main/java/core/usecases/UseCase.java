package core.usecases;

public interface UseCase<IN, OUT> {

    OUT execute(IN input);
}
