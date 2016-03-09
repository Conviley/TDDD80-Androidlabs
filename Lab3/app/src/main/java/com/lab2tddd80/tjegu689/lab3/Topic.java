package com.lab2tddd80.tjegu689.lab3;

/**
 * Created by Tjelvar Guo on 2016-03-09.
 */
public class Topic {
    private String topic;
    private String person;
    private String email;
    private String reply;

    public Topic(String topic, String name, String email, String reply) {
        this.topic = topic;
        this.person = name;
        this.email = email;
        this.reply = reply;
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

}
