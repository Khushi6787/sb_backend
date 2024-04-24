package DemoExample.SpringCrud.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DemoExample.SpringCrud.Entity.ticket;

public interface ticketRepository extends JpaRepository<ticket, Integer>{

	void deleteById(int tid);

	@Query("Select t from ticket t where t.user.id=:id")
	List<ticket> findByUid(Integer id, Pageable pageable);
	
}
