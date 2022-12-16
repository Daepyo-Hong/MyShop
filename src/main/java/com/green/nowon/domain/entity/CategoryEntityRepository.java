package com.green.nowon.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long>{

	Optional<CategoryEntity> findByName(String name);
	//1차카테고리 조회
	Optional<CategoryEntity> findByParentNoNullAndName(String name);
	//sub카테고리 조회
	Optional<CategoryEntity> findByParentNoAndName(Long parentNo, String name);
	
	List<CategoryEntity> findByDepthOrderByNameAsc(int depth);
	
	List<CategoryEntity> findByParentNoIsNullOrderByNameAsc();
	List<CategoryEntity> findByParentNoOrderByNameAsc(Long parentNo);

}
