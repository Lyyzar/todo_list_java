package org.example;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

public class Task {

    private String name;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;

    public Task(String name, String description, LocalDate dueDate, boolean isCompleted){
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void toggleIsCompleted() {
        isCompleted = !isCompleted;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name=" + name  +
                ", description=" + description +
                ", dueDate=" + dueDate +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
