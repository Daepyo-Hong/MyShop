package com.green.nowon.domain.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @JoinColumn(name = "member_no")
    @OneToOne
    private MemberEntity member;



}
