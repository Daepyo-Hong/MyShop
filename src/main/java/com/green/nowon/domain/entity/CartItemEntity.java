package com.green.nowon.domain.entity;

import javax.persistence.*;

@Entity
public class CartItemEntity {
    //같은상품을 여러 개 주문할 수 있으므로 개수표현을 위한 entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    private int count; //수량

    @JoinColumn // fk : cart_no
    @ManyToOne
    private CartEntity cart;

    @JoinColumn
    @ManyToOne
    private ItemEntity item;
}
