package io.zerogone.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blog_member_invitation_key")
public class BlogMemberInvitationKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String value;

    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @OneToOne
    @JoinColumn(name = "blog_member_id", referencedColumnName = "id")
    private BlogMember owner;

    public BlogMemberInvitationKey() {

    }

    public BlogMemberInvitationKey(String value, LocalDateTime createDateTime, BlogMember owner) {
        this.value = value;
        this.createDateTime = createDateTime;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }
}
