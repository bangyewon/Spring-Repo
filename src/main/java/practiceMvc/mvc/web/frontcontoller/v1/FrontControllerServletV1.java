package hello.servlet.web.frontcontoller.v1;

import hello.servlet.web.frontcontoller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontoller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontoller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//"/front-controller/v1/*" : v1하위에 어떤게 들어와도 무조건 호출이됨
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String,ControllerV1> controllerMap = new HashMap<>();

    //생성자
    public FrontControllerServletV1() {
        //앞이 호출되면 뒤가 실행됨
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.serivce");
        //다형성 부분 잘 이해하기!!
        String requestURI = request.getRequestURI(); //위의 /front-controller/v1/members/new-form 얻어내기 가능
        //인터페이스로 꺼내게되면 하위 코드를 일관적으로 사용가능
        //controllerMap의 key는 /front-controller/v1/members/new-form
        //다형성에 의해서 인터페이스로 받을 수 있음
        ControllerV1 controller = controllerMap.get(requestURI); // controller에서 찾아짐
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request,response); // 인터페이스 호출
    }
}
