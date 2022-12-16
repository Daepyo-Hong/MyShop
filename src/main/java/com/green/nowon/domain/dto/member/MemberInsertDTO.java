package com.green.nowon.domain.dto.member;

import com.green.nowon.domain.entity.MemberEntity;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter
public class MemberInsertDTO {
	
	private String email;
	private String pass;
	private String name;
	
	//최종적으로DB에 저장될때는 dto의 필드가 MemberEntity클래스에 매핑
	public MemberEntity toEntity(PasswordEncoder pe) {
		return MemberEntity.builder()
				.email(email).pass(pe.encode(pass)).name(name)
				.build();
	}

}
