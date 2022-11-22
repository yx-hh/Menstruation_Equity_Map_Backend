package edu.uci.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    public void sendMail(String subject, String mailMessage){
        log.info(this.getClass().getName()+ ".SendMail start!");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receivers);
        message.setSubject(subject);
        message.setText(mailMessage);
        emailSender.send(message);

        log.info(this.getClass().getName()+ ".SendMail end!");
    }

}
