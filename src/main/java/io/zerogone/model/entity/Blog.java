package io.zerogone.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int id;

    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    @Column(updatable = false)
    private String introduce;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.PERSIST)
    private final List<BlogMember> members = new ArrayList<>();

    @Column(name = "invitation_key", nullable = false, updatable = false)
    private String invitationKey;

    Blog() {

    }

    public Blog(String name, String introduce, String imageUrl, String invitationKey) {
        this.name = name;
        this.introduce = introduce;
        this.imageUrl = imageUrl;
        this.invitationKey = invitationKey;
    }

    public Blog(int id, String name, String introduce, String imageUrl, String invitationKey) {
        this(name, introduce, imageUrl, invitationKey);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void addMember(BlogMember member) {
        members.add(member);
    }

    public List<BlogMember> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public String getInvitationKey() {
        return invitationKey;
    }
}
