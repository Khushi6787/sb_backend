package DemoExample.SpringCrud.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DemoExample.SpringCrud.Entity.department;
import DemoExample.SpringCrud.Entity.otpTb;
import DemoExample.SpringCrud.Entity.priority;
import DemoExample.SpringCrud.Entity.roles;
import DemoExample.SpringCrud.Entity.user_mst;
import DemoExample.SpringCrud.Repository.depRepository;
import DemoExample.SpringCrud.Repository.otpRepository;
import DemoExample.SpringCrud.Repository.priorityRepository;
import DemoExample.SpringCrud.Repository.rolesRrepository;
import DemoExample.SpringCrud.Repository.ticketRepository;
import DemoExample.SpringCrud.Repository.user_mstRepository;
import DemoExample.SpringCrud.Service.ServiceImp.emailService;
import DemoExample.SpringCrud.Service.ServiceImp.ticketService;
import DemoExample.SpringCrud.Service.ServiceImp.userService;
import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "http:localhost:3000")
public class userMainController {
	@Autowired
	public userService us;

	@Autowired
	public ticketService ts;
	@Autowired
	public depRepository depRepository;
	
	@Autowired
	public priorityRepository priorityRepository;

	@Autowired
	public user_mstRepository user_mstRepository;

	@Autowired
	public rolesRrepository rolesRrepository;

	@Autowired
	public otpRepository otpRepository;
	
	@Autowired
	public ticketRepository ticketRepository;

	@CrossOrigin
	@PostMapping("/users")
	public String createUser(@RequestBody user_mst user) {
		try {
			user_mstRepository.save(user);
			return "User created successfully";
		} catch (Exception e) {
			return "Failed to create user. Error: " + e.getMessage();
		}
	}

	@CrossOrigin
	@PostMapping("/verifyOTP")
	public String verifyOTP(@RequestBody otpTb request) {
		otpTb otpRecord = otpRepository.findByEmail(request.getEmail());
		System.out.println("Retrieved OTP record: " + otpRecord);

		if (otpRecord != null) {
			System.out.println("Retrieved OTP code: " + otpRecord.getCode());
			System.out.println("User-provided OTP: " + request.getCode());

			if (otpRecord.getCode().equals(request.getCode())) {
				return "OTP verified successfully";
			} else {
				return "Incorrect OTP";
			}
		} else {
			return "No OTP found for the provided email";
		}
	}

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	public userMainController(user_mstRepository userMstRepository, BCryptPasswordEncoder passwordEncoder) {
//		this.user_mstRepository = userMstRepository;
//		this.passwordEncoder = passwordEncoder;
//	}
	
	@GetMapping("getByname/{userName}")
    public ResponseEntity<user_mst> getUserByName(@PathVariable String userName) {
        user_mst user = user_mstRepository.findByUserName(userName);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getUserById/{id}")
    public String getUserNameById(@PathVariable Integer id) {
         user_mst user = user_mstRepository.findById(id).orElse(null);
         if (user != null) {
             return user.getUser_name();
         } else {
             return "User not found";
         }
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<user_mst> getUserByEmail(@PathVariable String email) {
        user_mst user = user_mstRepository.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/roles/customer")
    public List<user_mst> getUsersByCustomerRole() {
        return user_mstRepository.findByRoleCustomer();
    }
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody user_mst loginUser) {
        user_mst user = user_mstRepository.findByUserName(loginUser.getUser_name());
        
        if (user != null) {
            if (loginUser.getPassword().equals(user.getPassword())) {
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("role", user.getRoles()); 
                responseData.put("uid", user.getUid()); 
                return ResponseEntity.ok(responseData);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


	@CrossOrigin
	@GetMapping("/departments")
	public List<department> getAllDepartments() {
		return depRepository.findAll();
	}
	@CrossOrigin
	@GetMapping("/priority")
	public List<priority> getPriorities() {
		return priorityRepository.findAll();
	}

	@CrossOrigin
	@GetMapping("/roles")
	public List<roles> getAllRoles() {
		return rolesRrepository.findAll();
	}
	
	@GetMapping("/getAllUser")
    public List<user_mst> getAllUser() {
        return user_mstRepository.findAll();
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<user_mst> getUser(@PathVariable Integer id) {
        user_mst user = user_mstRepository.findById(id).get();
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	
}
