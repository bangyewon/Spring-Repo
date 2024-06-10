package demoHello.demo.helloSpring.Controller;

import org.springframework.ui.Model;
import demoHello.demo.helloSpring.domain.Member;
import demoHello.demo.helloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//컴포넌트 스캔과 자동 의존관계 설정하기 - 회원 컨트롤러가 회원 리포지토리 사용할 수 있도록 의존관계 준비
// - 회원 컨트롤러에 의존관계 추가하기
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public  MemberController(MemberService memberService) { // 이상태로는 configuration 에러로 스프링 빈 등록해줘야함
        this.memberService = memberService;
    }
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    //회원 컨트롤러에서 회원 실제 등록하는 기능
    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
    //회원 조회 기능
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
