package com.springJDBCAJAX.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
    private JavaMailSender sender;
	
	
		 public String sendSaveMail() {
		        MimeMessage message = sender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper(message);

		        try {
		            helper.setTo("<email To>");
		            helper.setText("Data added successfully.");
		            helper.setSubject("Mail to add data.");
		        } catch (MessagingException e) {
		            e.printStackTrace();
		            return "Error while sending mail ..";
		        }
		        sender.send(message);
		        return "Mail Sent Success!";
		    }		
		 
		 public String sendDeleteMail() {
		        MimeMessage message = sender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper(message);

		        try {
		            helper.setTo("<email To>");
		            helper.setText("Data deleted successfully.");
		            helper.setSubject("Mail To delete data.");
		        } catch (MessagingException e) {
		            e.printStackTrace();
		            return "Error while sending mail ..";
		        }
		        sender.send(message);
		        return "Mail Sent Success!";
		    }		
		 
		 public String sendUpdateMail() {
		        MimeMessage message = sender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper(message);

		        try {
		            helper.setTo("<email To>");
		            helper.setText("Data updated successfully.");
		            helper.setSubject("Mail to update data.");
		        } catch (MessagingException e) {
		            e.printStackTrace();
		            return "Error while sending mail ..";
		        }
		        sender.send(message);
		        return "Mail Sent Success!";
		    }		

	
}
