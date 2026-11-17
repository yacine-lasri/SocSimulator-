package soc.dao;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void add(T item);

    Optional<T> findById(long id);

    List<T> findAll();

    void update(T item);

    void delete(long id);
}