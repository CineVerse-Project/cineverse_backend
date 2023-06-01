/**
 * Created At {19 May 2023
 * By HuuNQ
 */
package fa.cineverse.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
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
* EmailServiceImpl
*
* Version: 1.0
*
* Date: May 30, 2023
*
* Copyright
*
* Modification Log:
*
* DATE          AUTHOR          DESCRIPTION 
* -----------------------------------------
* May 30, 2023  HuuNQ               
*
*/
@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Configuration configuration;
	
	 /**
	 * sendEmail
     * @param to
     * @param tokenPassword
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     * @throws IOException
     * @throws TemplateException
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
	 * getEmailContent
	 * @author HuuNQ
	 * @param to
	 * @param tokenPassword
	 * 19 May 2023
	 * @return String
	 * @throws IOException
	 * @throws TemplateException
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
