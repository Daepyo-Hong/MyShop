package com.green.nowon.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;
    private String name;

    @JoinColumn     //parent_no
    @ManyToOne(fetch = FetchType.LAZY)  //순환참조문제 방지위해 LAZY 로!
    private Category parent;        //상위카테고리 접근하기 위해 양방향설정

    @Builder.Default
    @OneToMany(mappedBy = "parent") //연계테이블 생성방지위해 mappedBy 사용
    private List<Category> child = new ArrayList<>();

    //편의메서드
    public Category addChildCategory(Category category){
        child.add(category);
//        category.parent=this;
        return this;
    }
}
