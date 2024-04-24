package DemoExample.SpringCrud.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import DemoExample.SpringCrud.Entity.otpTb;
import DemoExample.SpringCrud.Entity.user_mst;

public interface otpRepository extends JpaRepository<otpTb, Integer> {
	otpTb findByUser(user_mst user);
	otpTb findByEmail(String email);
}
