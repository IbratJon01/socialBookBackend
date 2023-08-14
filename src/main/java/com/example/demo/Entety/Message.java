package com.example.demo.Entety;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users sender;
    @ManyToOne
    private Users receiver;
    private String content;
    private Date timestamp;

    private boolean isRead;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
