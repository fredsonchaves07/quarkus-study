package github.fredsonchaves07.core.usecases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.valueObject.ValueObject;

public interface SingleUseCase<IN extends ValueObject> {

    Either<Error, ValueObject> execute(IN input);
}
