package core.entities;

import java.time.LocalDateTime;

public abstract class Entity<I extends Identifier> {

    private final I id;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Entity(I id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String id() {
        return id.toString();
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
