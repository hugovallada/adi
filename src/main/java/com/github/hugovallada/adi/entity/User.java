package com.github.hugovallada.adi.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String username;

    private String email;

    private String password;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimeStamp;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * @deprecated "only for hibernate"
     */
    @Deprecated
    public User() {
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

}
