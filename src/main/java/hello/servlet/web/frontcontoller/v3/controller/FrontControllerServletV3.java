package hello.servlet.web.frontcontoller.v3.controller;
import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v2.ControllerV2;
import hello.servlet.web.frontcontoller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    //생성자
    public FrontControllerServletV3() {
        //앞이 호출되면 뒤가 실행됨
        controllerMap.put("/front-controller/v3/members/new-formfe", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //다형성 부분 잘 이해하기!!
        String requestURI = request.getRequestURI(); //위의 /front-controller/v1/members/new-form 얻어내기 가능
        //인터페이스로 꺼내게되면 하위 코드를 일관적으로 사용가능
        //controllerMap의 key는 /front-controller/v1/members/new-form
        //다형성에 의해서 인터페이스로 받을 수 있음
        ControllerV3 controller = controllerMap.get(requestURI); // controller에서 찾아짐
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //하단 로직은 구체적인 로직이기에 메소드로 만드는 것이 현명함 (control + command + M)
        Map<String, String> paramMap = createParmMap(request);
        ModelView mv =  controller.process(paramMap);

        String viewName = mv.getViewName();//논리이름 new-form으로 바꾸기
        //new-form
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);
    }

    //논리이름 갖고 물리이름 바꾸면서 반환해주는 메소드로 변경하기
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    //request,response가 아닌 paramMap 넘겨줘야함
    //key변수명 : paramName / value :request.getParameter(paramName)로 모든걸 갖고옴
    private static Map<String, String> createParmMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}


