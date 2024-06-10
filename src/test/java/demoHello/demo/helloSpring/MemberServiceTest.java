package demoHello.demo.helloSpring;

import demoHello.demo.helloSpring.domain.Member;
import demoHello.demo.helloSpring.repository.MemoryMemberRepository;
import demoHello.demo.helloSpring.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

//회원 서비스 테스트 하기
public class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    public void 회원가입() throws Exception {
        //g
        Member member = new Member();
        member.setName("hello");
        //w
        Long saveId = memberService.join(member);
        //t
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(),findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //g
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //w
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        //t
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
