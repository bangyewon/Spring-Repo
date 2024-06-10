package demoHello.demo.helloSpring.repository;

import demoHello.demo.helloSpring.domain.Member;

import java.util.List;
import java.util.Optional;
//인터페이스로 회원 객체 저장하는 리포지토리를 만듬
//인터페이스로 해당 기능들을 간단히 소개함
public interface MemberRepository {

    Member save(Member member); //회원 정보 저장 기능
    //findByid/findByName : 아이디와 이름 찾아서 가져오기
    //null이 나올 수 도 있다 -> Optinal로 감싸서 가져오기
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //전체 회원 리스트 찾아서 가져와야 하기에 List<Member>로 갖고오기
    List<Member> findAll();
}
