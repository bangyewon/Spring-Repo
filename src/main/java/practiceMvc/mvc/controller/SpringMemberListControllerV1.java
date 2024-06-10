package practiceMvc.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import practiceMvc.mvc.domain.member.Member;
import practiceMvc.mvc.domain.member.MemberRepository;

import java.util.List;

@Controller
public class SpringMemberListControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {

        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", members);
        return mv;

    }
}
