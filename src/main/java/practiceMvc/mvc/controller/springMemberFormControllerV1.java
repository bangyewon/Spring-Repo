package practiceMvc.mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller
@Component
@RequestMapping //해당 3개의 어노테이션중에 하나라도 클래스 레벨에 있으면 RequestMappingHandler가 찾음
public class springMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
