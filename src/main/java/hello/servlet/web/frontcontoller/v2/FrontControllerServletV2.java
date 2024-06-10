package hello.servlet.web.frontcontoller.v2;
import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontoller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontoller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


    @WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
    public class FrontControllerServletV2 extends HttpServlet {

        private Map<String, ControllerV2> controllerMap = new HashMap<>();

        //생성자
        public FrontControllerServletV2() {
            //앞이 호출되면 뒤가 실행됨
            controllerMap.put("/front-controller/v2/members/new-formfe", new MemberFormControllerV2());
            controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
            controllerMap.put("/front-controller/v2/members/members", new MemberListControllerV2());
        }

        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("FrontControllerServletV1.serivce");
            //다형성 부분 잘 이해하기!!
            String requestURI = request.getRequestURI(); //위의 /front-controller/v1/members/new-form 얻어내기 가능
            //인터페이스로 꺼내게되면 하위 코드를 일관적으로 사용가능
            //controllerMap의 key는 /front-controller/v1/members/new-form
            //다형성에 의해서 인터페이스로 받을 수 있음
            ControllerV2 controller = controllerMap.get(requestURI); // controller에서 찾아짐
            if (controller == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            MyView view =  controller.process(request, response); // 인터페이스 호출
        }
    }


