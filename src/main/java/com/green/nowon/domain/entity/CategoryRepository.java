package com.green.nowon.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String categoryName);

    //상위 카테고리가 null인 카테고리 : 1차 카테고리
    List<Category> findAllByParentNoIsNull();

    List<Category> findAllByParentNo(long parentNo);
}
