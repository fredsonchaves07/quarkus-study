package github.fredsonchaves07.core.usecases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.valueObject.ValueObject;

public interface InputUseCase<IN extends ValueObject, C> {

    Either<C, T> execute(IN input);
}
