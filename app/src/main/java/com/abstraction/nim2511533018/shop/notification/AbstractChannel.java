package com.abstraction.nim2511533018.shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
    
    private int sentCount;
    private String lastDelivered = "";

    @Override public final boolean send(CustomerContact contact, String message) {
        if (contact == null || message == null || message.isBlank()) 
            return false;

        var recipient = resolveRecipient(contact);

        if (recipient == null || recipient.isBlank()) 
            return false;

        var prepared = prepare(message);

        if (!deliver(recipient, prepared)) 
            return false;

        sentCount++;
        lastDelivered = prepared;

        return true;
    }

    protected abstract String resolveRecipient(CustomerContact contact);
    protected abstract boolean deliver(String recipient, String message);
    protected String prepare(String message) { return message; }

    public int getSentCount() { return sentCount; }
    public String getLastDelivered() { return lastDelivered; }
}
