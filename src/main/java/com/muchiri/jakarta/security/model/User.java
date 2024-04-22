package com.muchiri.jakarta.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigInteger;

/**
 *
 * @author muchiri
 */
@Entity
@Table(name = "basic_auth_user")
public class User {

    @Id
    public BigInteger id;

    @Column(name = "password")
    public String password;

    @Column(name = "username", unique = true)
    public String username;
}
