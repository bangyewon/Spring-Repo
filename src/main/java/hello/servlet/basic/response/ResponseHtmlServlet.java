package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet",urlPatterns = "/resonse-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type : text/html;charset=utf-8
        //html 동적으로 생성 가능 !
        response.setContentType("text/html"); //컨텐트타입이 있어야 html이라고 인식해서 업로드함
        response.setCharacterEncoding("utf-8"); // 이거 안하면 한글 다 깨짐

        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<div>안녕 </div>");
        writer.println("</body>");
        writer.println("</html>");

    }
}
