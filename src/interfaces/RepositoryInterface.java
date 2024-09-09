package interfaces;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {
    Optional<T> save(T object);
    Optional<T> update(T object);
    Optional<T> find(String id);
    Optional<T> remove(T object);
    List<T> findAll();
}