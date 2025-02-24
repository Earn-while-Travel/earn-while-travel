package com.nithish.ewt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user_info",schema="EARN_WHILE_TRAVEL")
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_gmail", nullable = false, unique = true)
    private String userGmail;

    @Column(name = "user_register_nbr", nullable = false, unique = true)
    private String userRegisterNbr;

    // Getters & Setters
}
