package cli.tasktracker;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Task {
    private final int id;
    private final String description;
    private TaskStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Task(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nDescription: " + description +
                "\nStatus: " + status +
                "\nCreated At: " + createdAt +
                (this.updatedAt != null
                        ? "\nUpdated At: " + updatedAt
                        : "\nChưa cập nhật");
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static class Builder {
        private final int id;
        private String description;

        private TaskStatus status = TaskStatus.TODO;
        private final LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder(int id, String description, LocalDateTime createdAt) {
            if (description == null) {
                throw new IllegalArgumentException("description cannot be null");
            }

            if (description.isBlank()) {
                throw new IllegalArgumentException("description cannot be blank");
            }

            this.id = id;
            this.description = description;
            this.createdAt = createdAt;
        }

        public Builder setTimeUpdate() {
            this.updatedAt = LocalDateTime.now();
            return this;
        }

        public Builder setStatusDone() {
            if (this.status == TaskStatus.DONE) {
                throw new IllegalStateException("Task is already DONE");
            }

            this.status = TaskStatus.DONE;
            return this;
        }

        public Builder setStatusInProgress() {
            if (this.status == TaskStatus.IN_PROGRESS) {
                throw new IllegalStateException("Task is already IN_PROGRESS");
            }

            this.status = TaskStatus.IN_PROGRESS;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

}
