package soc.service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import soc.dao.Repository;
import soc.exception.EntityNotFoundException;
import soc.model.Analyst;
import soc.model.AnalystLevel;

public class AnalystService {

    private final Repository<Analyst> repository;
    private final AtomicLong idCounter;

    public AnalystService(Repository<Analyst> repository) {
        this.repository = Objects.requireNonNull(
                repository, "Repository must not be null");

        long lastId = repository.findAll().stream()
                .mapToLong(Analyst::getId)
                .max()
                .orElse(0L);

        this.idCounter = new AtomicLong(lastId);
    }

    public Analyst create(String name, AnalystLevel level) {
        long id = idCounter.incrementAndGet();

        Analyst analyst = new Analyst(id, name, level);
        repository.add(analyst);

        return analyst;
    }

    public Analyst findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Analyst not found: " + id));
    }

    public List<Analyst> findAll() {
        return repository.findAll();
    }

    public Analyst update(long id, String name, AnalystLevel level) {
        findById(id);

        Analyst updated = new Analyst(id, name, level);
        repository.update(updated);

        return updated;
    }

    public void delete(long id) {
        repository.delete(id);
    }
}