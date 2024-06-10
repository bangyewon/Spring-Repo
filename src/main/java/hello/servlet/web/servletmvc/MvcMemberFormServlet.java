package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //할일 없이 그냥 jsp로 가면됨
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //컨트롤러에서 view로 이동시 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // 다른 서블릿or jsp로 이동할 수 있음 - 서버 내부에서 다시호출 / 리다이렉트는 아님
        dispatcher.forward(request,response);
    }
}
