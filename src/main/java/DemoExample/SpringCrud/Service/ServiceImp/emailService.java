package DemoExample.SpringCrud.Service.ServiceImp;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import DemoExample.SpringCrud.Entity.otpTb;
import DemoExample.SpringCrud.Repository.otpRepository;

@Service
public class emailService {
	@Autowired
	private JavaMailSender mailsender;

//	@Autowired
//	private user_mstRepository user_mstRepository;
//
	@Autowired
	otpRepository otpRepository;

	public void sendOtpEmail(String toEmail) {
		String otp = generateOtp();

		// Save OTP data to otpTB table
		otpTb otpData = new otpTb();
		otpData.setEmail(toEmail);
		otpData.setCode(otp);
		otpRepository.save(otpData);

		// Send OTP email
		String subject = "Your One-Time Password (OTP)";
		String body = "Your OTP is: " + otp;
		sendEmail(toEmail, subject, body);
	}

	private void sendEmail(String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("khushithakor.mscit19@vnsgu.ac.in");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);

		mailsender.send(message);
	}

	private String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
}
