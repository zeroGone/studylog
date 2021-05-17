package io.zerogone.model.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int id;

    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    @Column
    private String introduce;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.PERSIST)
    private final Set<BlogMember> members = new HashSet<>();

    @Column(name = "invitation_key", nullable = false, updatable = false)
    private String invitationKey;

    Blog() {

    }

    public Blog(int id, String name, String introduce, String imageUrl, String invitationKey) {
        this.name = name;
        this.introduce = introduce;
        this.imageUrl = imageUrl;
        this.invitationKey = invitationKey;
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

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addMember(BlogMember member) {
        members.add(member);
    }

    public Set<BlogMember> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public String getInvitationKey() {
        return invitationKey;
    }
}
