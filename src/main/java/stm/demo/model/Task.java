package stm.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import stm.demo.model.enums.Status;
import stm.demo.model.enums.Type;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String title;
    private String description;
    private LocalDate dateAdded;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @JsonIgnore
    @ManyToOne
    User user;

    public Task() {
    }

    public Task(String title, String description, LocalDate dateAdded, Type type, Status status, User user) {
        this.title = title;
        this.description = description;
        this.dateAdded = dateAdded;
        this.type = type;
        this.status = status;
        this.user = user;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
