package com.dh.digitalCar.services.servicesInterfaces;

public interface IEmailService {
    public Boolean sendEmail(String to, String subject, String text);
}
