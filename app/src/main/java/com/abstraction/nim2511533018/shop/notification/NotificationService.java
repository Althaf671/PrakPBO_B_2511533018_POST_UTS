package com.abstraction.nim2511533018.shop.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificationService {
    private final List<NotificationChannel> channels = new ArrayList<>();

    public void register(NotificationChannel channel) { 
        channels.add(Objects.requireNonNull(channel)); 
    }

    public int broadcast(CustomerContact contact, String message) {
        int delivered = 0;

        for (NotificationChannel channel : channels) 
            if (channel.send(contact, message)) delivered++;

        return delivered;
    }
}
