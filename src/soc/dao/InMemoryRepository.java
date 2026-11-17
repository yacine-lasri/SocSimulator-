package soc.dao;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.ToLongFunction;

import soc.exception.EntityNotFoundException;

public class InMemoryRepository<T> implements Repository<T> {

    private final Map<Long, T> items = new ConcurrentHashMap<>();
    private final ToLongFunction<T> idExtractor;

    public InMemoryRepository(ToLongFunction<T> idExtractor) {
        this.idExtractor = Objects.requireNonNull(
                idExtractor, "ID extractor must not be null");
    }

    @Override
    public void add(T item) {
        Objects.requireNonNull(item, "Item must not be null");

        long id = idExtractor.applyAsLong(item);

        if (items.putIfAbsent(id, item) != null) {
            throw new IllegalArgumentException(
                    "An item already exists with ID: " + id);
        }
    }

    @Override
    public Optional<T> findById(long id) {
        return Optional.ofNullable(items.get(id));
    }

    @Override
    public List<T> findAll() {
        return List.copyOf(items.values());
    }

    @Override
    public void update(T item) {
        Objects.requireNonNull(item, "Item must not be null");

        long id = idExtractor.applyAsLong(item);

        if (items.replace(id, item) == null) {
            throw new EntityNotFoundException(
                    "Item not found: " + id);
        }
    }

    @Override
    public void delete(long id) {
        if (items.remove(id) == null) {
            throw new EntityNotFoundException(
                    "Item not found: " + id);
        }
    }
}