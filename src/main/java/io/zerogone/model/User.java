package io.zerogone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private int id;

    private String name;
    
    @Column(nullable = false)
    private String email;
    @Column(name = "nick_name", nullable = false)
    private String nickName;
}
