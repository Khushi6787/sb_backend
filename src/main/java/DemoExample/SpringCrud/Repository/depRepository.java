package DemoExample.SpringCrud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import DemoExample.SpringCrud.Entity.department;

public interface depRepository extends JpaRepository<department, Integer>{

}
