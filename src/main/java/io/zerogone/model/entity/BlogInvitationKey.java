package io.zerogone.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blog_invitation_key")
public class BlogInvitationKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int id;

    @Column(nullable = false, updatable = false)
    private String value;

    @OneToOne
    @JoinColumn(name = "blog_member_id", referencedColumnName = "id", nullable = false, updatable = false)
    private BlogMember owner;

    BlogInvitationKey() {

    }

    public BlogInvitationKey(String value, BlogMember owner) {
        this.value = value;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getBlogName() {
        return owner.getBlogName();
    }

    public String getOwnerEmail() {
        return owner.getEmail();
    }

    public BlogMember getOwner() {
        return owner;
    }
}
