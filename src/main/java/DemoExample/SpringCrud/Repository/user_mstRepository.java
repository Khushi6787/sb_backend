package DemoExample.SpringCrud.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import DemoExample.SpringCrud.Entity.user_mst;
@Repository
public interface user_mstRepository extends JpaRepository<user_mst, Integer>{
	user_mst findByEmail(String email);
	
	@Query("SELECT u FROM user_mst u WHERE u.user_name = :user_name")
	user_mst findByUserName(@Param("user_name") String user_name);

	@Query("SELECT u FROM user_mst u JOIN u.roles r WHERE r.r_name = 'CUSTOMER'")
	List<user_mst> findByRoleCustomer();

	
//	user_mst findByUser_name(String user_name);

}
