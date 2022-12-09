package com.green.nowon.domain.entity;

import com.green.nowon.security.MyRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SequenceGenerator(name = "gen_mem", sequenceName = "seq_mem", allocationSize = 1)
@Table(name = "my_member")
@Entity
public class MemberEntity extends BaseDateEntity {

    @GeneratedValue(generator = "gen_mem", strategy = GenerationType.SEQUENCE)
    @Id
    private long mno;   //회원번호

    @Column(unique = true,nullable = false)   //unique not null
    private String email;   //username

    @Column(nullable = false)   //not null
    private String pass;    //password

    @Column(nullable = false)   //not null
    private String name;

    @Column(unique = true)
    private String nickName;

    private boolean social;     // 소셜유저여부
    private boolean deleted;    // 탈퇴여부

    //ROLE정보 --enum 사용
    @Builder.Default
    @CollectionTable(name = "my_role")
    @Enumerated(EnumType.STRING)    //저장유형 문자열로(롤 확장시 유리) 기본 ordinal(숫자)
    @ElementCollection(fetch = FetchType.EAGER) //1:N member테이블에서만 접근가능한 내장테이블?로 만들어줌
    private Set<MyRole> roles = new HashSet<>();
    //role적용을 위한 편의메서드
    public MemberEntity addRole(MyRole role){
        this.roles.add(role);
        return this;
    }
}
