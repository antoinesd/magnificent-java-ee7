package org.lab.javaee.chat;

/**
 * @author Antoine Sabot-Durand
 */
public class Message {

    private String user;

    private String content;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}