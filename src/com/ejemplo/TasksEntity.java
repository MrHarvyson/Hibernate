package com.ejemplo;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tasks", schema = "northwind", catalog = "")
public class TasksEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "task_id")
    private int taskId;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "start_date")
    private Date startDate;
    @Basic
    @Column(name = "due_date")
    private Date dueDate;
    @Basic
    @Column(name = "status")
    private byte status;
    @Basic
    @Column(name = "priority")
    private byte priority;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksEntity that = (TasksEntity) o;
        return taskId == that.taskId && status == that.status && priority == that.priority && Objects.equals(title, that.title) && Objects.equals(startDate, that.startDate) && Objects.equals(dueDate, that.dueDate) && Objects.equals(description, that.description) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, title, startDate, dueDate, status, priority, description, createdAt);
    }
}
