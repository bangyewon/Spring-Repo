package demoHello.demo.helloSpring.Controller;
//회원 등록 컨트롤러
//웹 등록 화면에서 데이터 전달받을 폼 객체

import org.springframework.web.bind.annotation.PostMapping;

//html의 name 보고 MemberForm의 Name:setName 메소드에 스프링이 넣어주게 됨 - 우리는 getname으로 꺼내면 됨
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
