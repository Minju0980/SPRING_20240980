package com.example.demo.model.service;

import lombok.*; // 어노테이션 자동 생성
import com.example.demo.model.domain.Member;
import jakarta.validation.constraints.*;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Data // getter, setter, toString, equals 등 자동 생성
public class AddMemberRequest {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Pattern(regexp = "^[^!@#$%^&*(),.?\":{}|<>]+$", message = "이름에는 특수문자를 사용할 수 없습니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "비밀번호는 8자 이상, 대문자와 소문자를 포함해야 합니다.")
    private String password;

    @Pattern(regexp = "^(19|[2-8][0-9]|90)$", message = "나이는 19세 이상 90세 이하만 입력 가능합니다.")
    private String age;

    private String mobile;


    private String address;

    public Member toEntity(){ // Member 생성자를 통해 객체 생성
        return Member.builder()
            .name(name)
            .email(email)
            .password(password)
            .age(age)
            .mobile(mobile)
            .address(address)
            .build();
    }
}
