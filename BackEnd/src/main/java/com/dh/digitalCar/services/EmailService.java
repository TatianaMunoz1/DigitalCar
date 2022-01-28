package com.dh.digitalCar.services;

import com.dh.digitalCar.services.servicesInterfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Boolean sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rentalcar.ar@outlook.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            emailSender.send(message);
            return true;
        } catch (Exception e) {
            //logger error
            System.out.println(e);
        }

        return false;
    }
}
