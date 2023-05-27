/**
 * Created At {19 May 2023
 * By HuuNQ
 */
package fa.cineverse.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import fa.cineverse.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * @author HuuNQ
 *
 * 19 May 2023
 * 
 */
@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Configuration configuration;
	/**
	 * @author HuuNQ
	 *
	 * 19 May 2023
	 * 
	 */
	@Override
	public void sendEmail(String to,String tokenPassword) throws MessagingException, IOException, TemplateException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("cineverse.sp@gmail.com","Cineverse Cinema Support");
        helper.setTo(to);
        String subject = "Reset your password!";
        String content = getEmailContent(to,tokenPassword);
        helper.setSubject(subject);
        helper.setText(content);
        javaMailSender.send(message);
    }
	/**
	 * @author HuuNQ
	 *
	 * 19 May 2023
	 * 
	 */
	String getEmailContent(String to, String tokenPassword) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("to", to);
        model.put("tokenPassword", tokenPassword);
        configuration.getTemplate("/mailSender.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
