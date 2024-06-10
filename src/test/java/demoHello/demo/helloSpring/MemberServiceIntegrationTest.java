package demoHello.demo.helloSpring;

import demoHello.demo.helloSpring.domain.Member;
import demoHello.demo.helloSpring.repository.MemberRepository;
import demoHello.demo.helloSpring.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

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
                () -> memberService.join(member2)); // 예외 발생해야함
        //t
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
