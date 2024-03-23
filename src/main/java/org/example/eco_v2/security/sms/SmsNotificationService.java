package org.example.eco_v2.security.sms;

public interface SmsNotificationService {
    void sendNotification(String phoneNumber, String message);
}
