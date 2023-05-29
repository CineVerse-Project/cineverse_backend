/**
 * Created At {19 May 2023
 * By HuuNQ
 */
package fa.cineverse.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import freemarker.template.TemplateException;

/**
 * @author HuuNQ
 *
 * 19 May 2023
 *
 */
public interface EmailService {
	void sendEmail(String to,String tokenPassword) throws UnsupportedEncodingException, MessagingException, IOException, TemplateException;
}
