package DemoExample.SpringCrud.Entity;

import jakarta.persistence.*;

public class ticket_mst {
	@ManyToOne
    @JoinColumn(name = "did")
    private int department;
}
