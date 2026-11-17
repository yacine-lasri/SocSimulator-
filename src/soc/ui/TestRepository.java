package soc.ui;

import soc.dao.InMemoryRepository;
import soc.dao.Repository;
import soc.exception.EntityNotFoundException;
import soc.model.Analyst;
import soc.model.AnalystLevel;

public class TestRepository {

    public static void main(String[] args) {

        Repository<Analyst> repository =
                new InMemoryRepository<>(Analyst::getId);

        // Ajouter
        repository.add(new Analyst(1, "Saad", AnalystLevel.N1));

        // Chercher
        Analyst analyst = repository.findById(1).orElseThrow();
        System.out.println("Name : " + analyst.getName());

        // Modifier en conservant le même identifiant
        repository.update(
                new Analyst(1, "Saad", AnalystLevel.N2)
        );

        System.out.println("Updated level : "
                + repository.findById(1).orElseThrow().getLevel());

        // Vérifier le refus d'un doublon
        try {
            repository.add(
                    new Analyst(1, "Other", AnalystLevel.N1)
            );
            throw new AssertionError("Duplicate ID was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate rejected");
        }

        // Supprimer
        repository.delete(1);
        System.out.println("Remaining : "
                + repository.findAll().size());

        // Vérifier le refus d'une modification inexistante
        try {
            repository.update(analyst);
            throw new AssertionError("Missing item was updated");
        } catch (EntityNotFoundException e) {
            System.out.println("Missing item rejected");
        }
    }
}