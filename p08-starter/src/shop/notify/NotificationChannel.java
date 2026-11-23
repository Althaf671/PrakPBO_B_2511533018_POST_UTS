package shop.notify;

/** Saluran pengiriman notifikasi ke pelanggan. */
public interface NotificationChannel
{
    void send(String recipient, String message);
}
