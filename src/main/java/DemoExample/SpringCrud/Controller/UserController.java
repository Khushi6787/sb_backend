package DemoExample.SpringCrud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import DemoExample.SpringCrud.Entity.User;
import DemoExample.SpringCrud.Service.UserInterface;

@RestController
public class UserController {
	@Autowired
	UserInterface service;
	@GetMapping("/get")
	public List<User>getdata() {
		return service.getAllUser();
	}
	
	

}
