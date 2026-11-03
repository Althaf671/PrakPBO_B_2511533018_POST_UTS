package com.abstraction.nim2511533018.shop.notification;

public interface NotificationChannel {
    String channelName();
    boolean send(CustomerContact contact, String message);
}
