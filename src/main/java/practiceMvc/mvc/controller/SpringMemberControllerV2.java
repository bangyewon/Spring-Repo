package practiceMvc.mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import practiceMvc.mvc.domain.member.Member;
import practiceMvc.mvc.domain.member.MemberRepository;

import java.util.List;
// 컨트롤러 통합
@Controller
@RequestMapping("/spring/v1/members")
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository =MemberRepository.getInstance();

//    @RequestMapping(value = "/new-form",method = RequestMethod.GET) // getMapping만 되도록 막기 가능
    @GetMapping("/new-form")
    public ModelAndView newform(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
    @RequestMapping
    public ModelAndView members() {

        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", members);
        return mv;

    }
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username,age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member",member);
        return mv;
    }
}
