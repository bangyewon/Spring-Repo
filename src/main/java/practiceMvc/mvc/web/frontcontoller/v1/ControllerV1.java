package hello.servlet.web.frontcontoller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {
    //서블릿과 똑같은 service 메소드 처럼 만들어줌
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
