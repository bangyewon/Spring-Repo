package hello.servlet.web.frontcontoller.v2;

import hello.servlet.web.frontcontoller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    //기존에는 void로 forward해서 jsp로 이동했던 것과 달리 MyView 만들어서 넘기기 가능하도록 인터페이스 설계함
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
