package com.abstraction.nim2511533018.shop.notification;

public class WhatsAppChannel extends AbstractChannel {
    @Override 
    protected String prepare(String message) { return "[Toko UMKM] " + message; }

    @Override 
    public String channelName() { return "WA"; }

    @Override 
    protected String resolveRecipient(CustomerContact contact) { 
        return (
            contact.getPhone()
                .startsWith("08") || contact.getPhone()
                .startsWith("+62")) && contact.getPhone()
                .length() >= 10 
                    ? contact.getPhone() 
                    : ""; 
    }

    @Override 
    protected boolean deliver(String recipient, String message) {
        System.out.println("[WA to " + recipient + "] " + message);
        return true;
    }
}
