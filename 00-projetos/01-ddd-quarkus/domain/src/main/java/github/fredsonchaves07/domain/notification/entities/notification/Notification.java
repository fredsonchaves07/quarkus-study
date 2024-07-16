package github.fredsonchaves07.domain.notification.entities.notification;

import github.fredsonchaves07.core.entities.Entity;
import github.fredsonchaves07.core.entities.Identifier;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Notification extends Entity<NotificationID> {

    private String title;

    private String content;

    private LocalDateTime readAt;

    private String recipientId;

    private Notification(NotificationID notificationID) {
        super(notificationID);
    }

    private Notification(String title, String content, String recipientId) {
        super(new NotificationID());
        this.title = title;
        this.content = content;
        this.recipientId = recipientId;
    }

    private Notification(String title, String content,  LocalDateTime readAt, String recipientId) {
        super(new NotificationID());
        this.title = title;
        this.content = content;
        this.readAt = readAt;
        this.recipientId = recipientId;
    }

    public static Notification create(String title, String content, String recipientId) {
        return new Notification(title, content, recipientId);
    }

    public static Notification create(String title, String content, LocalDateTime readAt, String recipientId) {
        return new Notification(title, content, readAt, recipientId);
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public LocalDateTime readAt() {
        return readAt;
    }

    public String recipientId() {
        return recipientId;
    }
}
