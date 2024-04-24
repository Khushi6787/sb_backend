package DemoExample.SpringCrud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import DemoExample.SpringCrud.Entity.user_mst;
import DemoExample.SpringCrud.Service.ServiceImp.emailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class emailController {
	@Autowired
	public emailService em;

	@PostMapping("/sendOTP")
	public String sendOtpEmail(@RequestBody user_mst user) {
	    try {
	        String email = user.getEmail();
	        em.sendOtpEmail(email);
	        return "OTP sent successfully to " + email;
	    } catch (Exception e) {
	        return "Failed to send OTP. Error: " + e.getMessage();
	    }
	}

}
