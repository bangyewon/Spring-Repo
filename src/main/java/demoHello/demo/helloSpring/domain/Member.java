package demoHello.demo.helloSpring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Member 도메인 <회원 객체> 만들기
//JPA 엔티티 매핑하기
@Entity
public class Member {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 값 넣으면 id 자동생성해줌 IDENTITY 전략
    private Long id;
    private String name;
    // id 얻고,지정
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //name 얻고,지정하기
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
