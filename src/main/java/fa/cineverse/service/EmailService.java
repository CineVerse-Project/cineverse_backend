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
* EmailService
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
public interface EmailService {
	/**
	 * @param to
	 * @param tokenPassword
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	void sendEmail(String to,String tokenPassword) throws UnsupportedEncodingException, MessagingException, IOException, TemplateException;
}
