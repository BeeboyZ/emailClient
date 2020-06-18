package email;

import java.util.Properties;

//import javax.mail.Authenticator;
import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class sendMail {
	public static void main(String[] args) {
		
		String username = "username";//사용자 이메일 계정
		String password = "password";//사용자 계정 비밀번호
		String receiver = "receivername";//보내는 이메일 계정
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host","smtp.gmail.com");//gmail용
		props.put("mail.smtp.port",465);
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.ssl.enable","true");
		props.put("mail.smtp.ssl.trust","smtp.gmail.com");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
				return new javax.mail.PasswordAuthentication(username,password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(receiver));
			
			message.setSubject("SMTP test");//메일 제목
			message.setText("testing");//메일 내용
			
			Transport.send(message);
			System.out.println("이메일이 전송되었습니다");
		} 
		catch(AddressException e) {
			e.printStackTrace();
		}
		catch(MessagingException e) {
			e.printStackTrace();
		}
	}
}
