package soc.ui;

import soc.dao.InMemoryRepository;
import soc.exception.EntityNotFoundException;
import soc.model.Analyst;
import soc.model.AnalystLevel;
import soc.service.AnalystService;

public class TestAnalystService {

    public static void main(String[] args) {

        AnalystService service = new AnalystService(
                new InMemoryRepository<Analyst>(Analyst::getId)
        );

        Analyst saad = service.create("Saad", AnalystLevel.N1);
        service.create("Yassine", AnalystLevel.N2);

        System.out.println("Analysts : " + service.findAll().size());
        System.out.println("Name : "
                + service.findById(saad.getId()).getName());

        service.update(saad.getId(), "Saad Fakir", AnalystLevel.N2);

        Analyst updated = service.findById(saad.getId());
        System.out.println("Updated name : " + updated.getName());
        System.out.println("Updated level : " + updated.getLevel());

        service.delete(saad.getId());

        System.out.println("Remaining : " + service.findAll().size());

        try {
            service.findById(saad.getId());
            throw new AssertionError("Deleted analyst was found");
        } catch (EntityNotFoundException e) {
            System.out.println("Missing analyst rejected");
        }
    }
}