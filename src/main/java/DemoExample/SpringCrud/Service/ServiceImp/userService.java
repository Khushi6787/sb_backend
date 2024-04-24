package DemoExample.SpringCrud.Service.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DemoExample.SpringCrud.Entity.department;
import DemoExample.SpringCrud.Entity.ticket;
import DemoExample.SpringCrud.Entity.user_mst;
import DemoExample.SpringCrud.Repository.depRepository;
import DemoExample.SpringCrud.Repository.user_mstRepository;
@Service
public class userService {
	 	@Autowired
	    private user_mstRepository userRepository;

	 	@Autowired
	 	private depRepository depRepository;
//	    public void saveUser(user_mst user) {
//	        userRepository.save(user);
//	    }
	 	 public void createUser(String userName, String email, String password, String address, int did) {
	        user_mst user = new user_mst();
	        user.setUser_name(userName);
	        user.setEmail(email);
	        user.setPassword(password);
	        user.setAddress(address);
	        department department = depRepository.findById(did).orElse(null);
	        if (department == null) {
	            throw new IllegalArgumentException("Department with ID " + did + " not found");
	        }
	        userRepository.save(user);
	    }
	 	 
	 	 
	 	public List<user_mst> findByRoleCustomer() {
	        return userRepository.findByRoleCustomer();
	    }
	 	 
	 	public user_mst getUserById(Integer uid) {
	 	    return userRepository.findById(uid).get();
	 	}
	 	
	 	
}
