package org.lab.javaee.chat;

import javax.enterprise.inject.Vetoed;
import javax.persistence.*;

/**
 * @author Antoine Sabot-Durand
 */

@Entity
@Vetoed
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
