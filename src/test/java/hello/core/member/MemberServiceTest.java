package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    //appConfig로 바꿔야함
//    MemberService memberService = new MemberServiceImpl();
    MemberService memberService;
    @BeforeEach // test실행 전 무조건 실행되는 것
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //g
        Member member = new Member(1L,"memberA",Grade.VIP);
        //w
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //t
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
