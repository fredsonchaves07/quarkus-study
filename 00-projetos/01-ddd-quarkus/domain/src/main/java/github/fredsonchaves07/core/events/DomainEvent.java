package github.fredsonchaves07.core.events;

import github.fredsonchaves07.core.entities.Identifier;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DomainEvent {

    LocalDate ocurredAt();

    Identifier aggregateId();
}
