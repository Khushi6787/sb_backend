package DemoExample.SpringCrud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import DemoExample.SpringCrud.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
}
