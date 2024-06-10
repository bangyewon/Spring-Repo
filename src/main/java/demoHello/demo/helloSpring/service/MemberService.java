package demoHello.demo.helloSpring.service;

import demoHello.demo.helloSpring.domain.Member;
import demoHello.demo.helloSpring.repository.MemberRepository;
import demoHello.demo.helloSpring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//회원 서비스 개발
//@Service // 회원 서비스 스프링 빈 등록
@Transactional // 서비스 계층에 트랜잭션 추가 -> JPA 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야한다.
public class MemberService {
    /**
     * 회원 서비스가 메모리 회원 리포지토리 직접 생성이 아닌 서비스 코드 DI 가능하게 변경하기
     */
//   private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    // 생성자에 Autowired 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈 찾아서 주입함 / 생성자 1개만 있을 시 생략 가능
//    @Autowired // 회원 서비스 스프링 빈 등록
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    // 중복 회원은 안됨 -> memberRepository에서 name 찾아 값 받아오기 -> 중복검사진행
    public Long join(Member member) {
        vaildateDuplicateMember(member); //member 중복검사
        memberRepository.save(member); // 회원 저장
        return member.getId(); //아이디로 반환
    }
    //중복검사 메소드
    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    //전체 회원 조회
    //리스트 형식의 findMembers메소드로 전체 검색하기
    public List<Member> findMembers() {
        return memberRepository.findAll(); //memberRepository의 findAll()메소드로 검색 후 반환
    }
    //id검색
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
