package com.green.nowon;

import com.green.nowon.domain.entity.Category;
import com.green.nowon.domain.entity.CategoryRepository;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.security.MyRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
class MyShopApplicationTests {
    @Autowired
    private MemberEntityRepository mrepo;
    @Autowired
    private PasswordEncoder pe;

    @Test
    void 어드민계정생성() {
        mrepo.save(MemberEntity.builder()
                        .email("admin@test.com")
                        .name("관리자").nickName("관리자")
                        .pass(pe.encode("1234"))
                .build()
                .addRole(MyRole.USER)
                .addRole(MyRole.ADMIN)
        );
    }

    @Autowired
    CategoryRepository categoryRepository;


    @Commit
    @Transactional      //테스트코드에서는 @Commit이 있어야함
    @Test
    void s_2차카테고리생성_테스트(){
        Category cate = categoryRepository.findByName("컴퓨터").orElseThrow();
        cate.addChildCategory(Category.builder().name("모니터").build());
        cate.addChildCategory(Category.builder().name("본체").build());

        categoryRepository.save(cate);
    }

    @Test
    void first_1차카테고리생성_테스트(){
        categoryRepository.save(Category.builder()
                        .name("의류")
                .build()
        );
    }

    @Test
    void sub_2차카테고리생성_manytoone이주컬럼(){
        categoryRepository.save(Category.builder()
                .name("하의")
                //상위카테고리설정
                .parent(Category.builder()
                        .no(9)
                        .build())
                .build()
        );
    }


}
