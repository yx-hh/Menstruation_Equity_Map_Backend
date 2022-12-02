package edu.uci.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Huang Yuxin
 * @date 2022/11/10
 */
@Slf4j
@Service
public class GmailHelper {


    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.receivers}")
    private String[] receivers;

    @Autowired
    private JavaMailSender emailSender;

    public void sendMail(String subject, String mailMessage) throws MessagingException {
        log.info(this.getClass().getName()+ ".SendMail start!");
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(receivers);
        helper.setSubject(subject);
        helper.setText(mailMessage, true);
        emailSender.send(message);

        log.info(this.getClass().getName()+ ".SendMail end!");
    }

}
