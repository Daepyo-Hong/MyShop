package com.green.nowon.security;

import com.green.nowon.domain.entity.MemberEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class MyUserDetails extends User {

    private String email;
    private String name;
    private String nickName;

    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    public MyUserDetails(MemberEntity entity){
        this(entity.getEmail(), entity.getPass(),
                entity.getRoles()
                .stream()
                .map(myRole -> new SimpleGrantedAuthority(myRole.getRole()))// "ROLE_USER" or "ROLE_ADMIN"
                .collect(Collectors.toSet()));
        this.email=entity.getEmail();
        this.name=entity.getName();
        this.nickName = entity.getNickName();    //null일 수 있으므로
    }
}
