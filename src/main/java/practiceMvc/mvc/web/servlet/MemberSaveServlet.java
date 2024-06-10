package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet",urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username"); // 파라미터 가져오기
        int age =Integer.parseInt(request.getParameter("age")); // age는 int인데 응답할땐string이므로 int로 바꾸기

        Member member = new Member(username,age);
        memberRepository.save(member);
        //결과를 html로 응답함
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write(
                "<html>\n" +
                        "<head>\n" +
                        " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                        "<body>\n" +
                        "성공\n" +
                        "<ul>\n" +
                        "    <li>id="+member.getId()+"</li>\n" +
                        "    <li>username="+member.getUsername()+"</li>\n" +
                        " <li>age="+member.getAge()+"</li>\n" + "</ul>\n" +
                        "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                        "</html>");
    }
}
