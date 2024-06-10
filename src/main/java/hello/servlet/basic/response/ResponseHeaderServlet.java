package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet",urlPatterns = "/reponse-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK); //의미있는 값으로 쓰이기에 의미 바로확인 가능

        //[reponse-headers]
        response.setHeader("Content-Type","text/plain;charset=utf-8"); //헤더에 직접 셋팅함
        response.setHeader("Content-Control","no-cache,no-store,must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("my-header","hello");

        //[Header 편의 메서드]
//        content(response);
//        cookie(response);
        redirect(response);

        //[message body]
        //메세지 바디에 getWriter 또는 getInputStream얻을 수 있음
        //거기에 원하는 메세지 넣어주면 메세지 바디에서 출력됨
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2 //임의로 길이 적을 수 있고 생략해도 자동으로 계산해서 나감
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain"); // 기본으로 됨
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    /**
     * 쿠키 넣기
     */
    private void cookie(HttpServletResponse response) {
    //Set-Cookie: myCookie=good;Max-Age=600; //key / value /초 넣으면 됨
    // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); // 셋팅해도됨
        //쿠키란 객체 만들어서 addCookie로 넣어주면 위와 똑같은 것을 보여줌
     Cookie cookie = new Cookie("myCookie", "good");
     cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    /**
     * 리다이렉트 하기
     */
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");
        //위 과정이 번거롭기에 아래 코드로 가능
        response.sendRedirect("/basic/hello-form.html");
    }
}
