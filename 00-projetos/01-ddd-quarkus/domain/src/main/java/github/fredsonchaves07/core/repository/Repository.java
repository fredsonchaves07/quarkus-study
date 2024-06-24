package github.fredsonchaves07.core.repository;

import github.fredsonchaves07.core.entities.Entity;
import github.fredsonchaves07.core.entities.Identifier;

import java.util.List;
import java.util.Optional;

public interface Repository<I extends Identifier, E extends Entity<I>> {

    I getId(E entity);

    void create(E entity);

    Optional<E> findById(I id);

    List<E> findAll();

    void update(E entity);

    void delete(E entity);

    default int count() {
        return findAll().size();
    }
}
