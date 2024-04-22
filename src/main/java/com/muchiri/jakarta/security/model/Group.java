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
@Table(name = "basic_auth_group")
public class Group {

    @Column(name = "id")
    @Id
    public BigInteger id;

    @Column(name = "name")
    public String name;

    @Column(name = "username")
    public String username;
}
