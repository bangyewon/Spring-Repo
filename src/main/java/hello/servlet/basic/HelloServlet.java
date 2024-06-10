package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet",urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    //서블릿이 호출되면 service 메소드가 호출됨
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " +username); // 쉽게 쿼리 파라미터를 읽어올 수 있는 것 증명

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); // 문자 인코딩 알려주기 두개의 코드는 컨텐트 타입 -> 헤더에 들어감
        response.getWriter().write("hello"+ username); // http의 body에 해당 객체가 들어감 / 서블릿 요청 응답 작동

    }
}
