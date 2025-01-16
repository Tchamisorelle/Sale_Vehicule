package com.sale_vehicule.catalog_service.observer;

public class EmailNotificationObserver implements Observer{
    private String email;

    public EmailNotificationObserver(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("Envoi d'un email a " + email + ": " + message);
    }
}
