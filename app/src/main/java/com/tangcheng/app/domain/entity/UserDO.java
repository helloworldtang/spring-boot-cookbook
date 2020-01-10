package com.tangcheng.app.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user")
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String email;

    @Column(name = "account_enabled")
    private Boolean accountEnabled;

    @Column(name = "account_expired")
    private Date accountExpired;

    @Column(name = "credentials_expired")
    private Date credentialsExpired;

    @Column(name = "account_locked")
    private Boolean accountLocked;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return account_enabled
     */
    public Boolean getAccountEnabled() {
        return accountEnabled;
    }

    /**
     * @param accountEnabled
     */
    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    /**
     * @return account_expired
     */
    public Date getAccountExpired() {
        return accountExpired;
    }

    /**
     * @param accountExpired
     */
    public void setAccountExpired(Date accountExpired) {
        this.accountExpired = accountExpired;
    }

    /**
     * @return credentials_expired
     */
    public Date getCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @param credentialsExpired
     */
    public void setCredentialsExpired(Date credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * @return account_locked
     */
    public Boolean getAccountLocked() {
        return accountLocked;
    }

    /**
     * @param accountLocked
     */
    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }
}