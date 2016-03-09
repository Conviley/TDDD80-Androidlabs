package com.lab2tddd80.tjegu689.lab3;

import java.io.Serializable;

/**
 * Created by Tjelvar Guo on 2016-03-09.
 */
public class Topic implements Serializable{
    private String topic;
    private String person;
    private String email;
    private String reply;

    public Topic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public String getPerson() {
        return person;
    }

    public String getEmail() {
        return email;
    }

    public String getReply() {
        return reply;
    }

    @Override
    public String toString() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
