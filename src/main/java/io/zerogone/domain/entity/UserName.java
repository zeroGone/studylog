package io.zerogone.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class UserName {
    @Column(name = "name", nullable = false, updatable = false)
    private String realName;

    @Column(name = "nick_name", nullable = false, unique = true, updatable = false)
    private String nickName;

    UserName() {

    }

    public UserName(String realName, String nickName) {
        this.realName = realName;
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserName userName = (UserName) o;
        return realName.equals(userName.realName) && nickName.equals(userName.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(realName, nickName);
    }
}
