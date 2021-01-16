package net.zhqu.website.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

/**
 * @author Kinglon
 * @version 1.0
 * @date 2020/9/2
 **/
@Component
public class MailUtil {
	private final Logger logger = LoggerFactory.getLogger(MailUtil.class);

	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	/*
	 * email:收件人
	 * emailMsg：发送的邮件内容
	 * subject:邮件主题
	 */
	public void sendMail(String email, String emailMsg, String subject){

		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			Properties props = System.getProperties();
			props.setProperty("mail.smtp.host", "smtp.mxhichina.com");
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtp.auth", "true");

			// 1.2)创建Authenticator验证器抽象类，重写方法getPasswordAuthentication()，返回它的子类：PasswordAuthentication()，指定用户名和密码进行验证。
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					/*
					 * 第一个参数 发邮件的用户名
					 * 第二个参数 用户名对应的密码
					 */
					return new PasswordAuthentication("operation@smartconns.com", "Smart2020");
				}
			};

			// 1.3) 创建Session对象，传入Properties和Authenticator的参数
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);

			// 2.1) 创建MimeMessage对象
			MimeMessage message = new MimeMessage(session);
			// 2.2) 设置发件人，与登录的用户名一致，需要使用到InternetAddress类型
			message.setFrom(new InternetAddress("operation@smartconns.com"));
			// 2.3) 设置一个收件人，指定收件人的类型：RecipientType.TO 发送  CC抄送  BCC密送
			// message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
			// 设置多个收件人
			String[] receives = email.split(",");
			InternetAddress[] adds = new InternetAddress[receives.length];
			for (int i = 0; i < adds.length; i++) {
				adds[i] = new InternetAddress(receives[i]);
			}
			message.setRecipients(MimeMessage.RecipientType.TO, adds);

			// 2.4) 设置邮件的主题
			message.setSubject(subject);
			// 2.5) 设置邮件的正文，需要指定内容类型和编码
			message.setContent(emailMsg, "text/html;charset=utf-8");
			message.saveChanges();
			// 3) 创建 Transport用于将邮件发送
			Transport.send(message);

			logger.debug("给 " + email + " 发送邮件成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("给 "+ email + " 发送邮件失败");
			throw new RuntimeException(e);
		}
	}
}
