package io.github.mp3cloud.controller.login;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.mp3cloud.dto.UserDTO;
import io.github.mp3cloud.service.IUserService;
import io.github.mp3cloud.service.Utility;
import net.bytebuddy.utility.RandomString;

@RestController
public class ForgotPasswordController {

//	@Autowired
//	private JavaMailSender javaMailSender;

	@Autowired
	private IUserService userService;

	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token = RandomString.make(30);
		final String fromEmail = "tranxuanvy1999@gmail.com"; // requires valid gmail id
		final String password = "kamakedied"; // correct password for gmail id
		try {
			userService.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;

			// cấu hình mail
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
			props.put("mail.smtp.port", "587"); // TLS Port
			props.put("mail.smtp.auth", "true"); // enable authentication
			props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

			// create Authenticator object to pass in Session.getInstance argument
			Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Session session = Session.getInstance(props, auth);

			sendEmail(session, email, resetPasswordLink);
			System.out.println(resetPasswordLink + "/t link");
		} catch (AccountNotFoundException ex) {
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error while sending email");
		}
		return "forgot_password_form";
	}

	public void sendEmail(Session session, String recipientEmail, String link)
			throws MessagingException, UnsupportedEncodingException {
		String subject = "Lấy lại mật khẩu";
		String content = "Xin chào," + "\n" + "Bạn có yêu cầu lấy lại mật khẩu." + "\n"
				+ "Nhấn vào đây để thay đổi mật khẩu của bạn: " + "\n" + " link" + "\n" + "\n"
				+ "Hãy bỏ qua email này nếu bạn đã nhớ mật khẩu";
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("mp3cloud_support@gmail.com", "Mp3Cloud Support"));
			msg.setReplyTo(InternetAddress.parse("mp3cloud_support@gmail.com", false));
			msg.setSubject(subject, "UTF-8");
			msg.setText(content, "UTF-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		UserDTO customer = userService.getByResetPasswordToken(token);
		model.addAttribute("title", "Reset your password");
		if (customer == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		} else {
			userService.updatePassword(customer, password);
			model.addAttribute("message", "You have successfully changed your password.");
		}
		return "message";
	}
}
