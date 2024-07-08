package github.fredsonchaves07.core.usecases;

import github.fredsonchaves07.core.errors.Either;
import github.fredsonchaves07.core.errors.Error;
import github.fredsonchaves07.core.valueObject.ValueObject;

public interface UseCase<IN extends ValueObject, OUT extends ValueObject> {

    Either<Error, OUT> execute(IN input);
}
