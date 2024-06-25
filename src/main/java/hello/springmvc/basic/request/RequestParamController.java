package hello.springmvc.basic.request;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        //age는 int이므로 integer로 파싱해야함
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={},age{}",username,age);

        response.getWriter().write("ok");
    }
    @ResponseBody // http 응답메세지에 ok라는 문자를 넣어서 바로 반환 == RestController
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 //@RequestParam("age") : age이름으로 http 바인딩함
                                 @RequestParam("age") int memberAge) {
        log.info("username={},age{}",memberName,memberAge);
        return "ok";
    }
    // 파라미터 이름 같으면 생략 가능 !
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={},age{}",username,age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-v4")
    // RequestParam 없앨 수 있음 단순타입 일때 (String,int,Integer ..)
    public String requestParamV4( String username, int age) {
        log.info("username={},age{}",username,age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-required")
    //required = false면 http에 해당 파라미터 없어도 됨 true면 반드시 있어야함
    public String requestParamRequired( @RequestParam(required = true) String username,
                                        @RequestParam(required = false) Integer age) {
        //int엔 null이 안되기에 int에 false사용하고 싶으면 Integer로 객체화시켜서 해야함

        log.info("username={},age{}",username,age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-default")
    //required = false면 http에 해당 파라미터 없어도 됨 true면 반드시 있어야함
    public String requestParamDefault( @RequestParam(required = true,defaultValue = "guest") String username,
                                        @RequestParam(required = false) Integer age) {
        //defaultValue가 있으면 required는 상관없음 -> 빈문자 까지 default값으로 처리해줌

        log.info("username={},age{}",username,age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    // 파라미터 값이 1개가 확실하면 map으로 아니라면 MultiValueMap으로 하기 - 보통은 하나를 씀
    public String requestParamMap(@RequestParam Map<String,Object> paramMap) {
        log.info("username={},age{}",paramMap.get("username"),paramMap.get("username"));
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/mdoel-attribute=-v1")
    //@RequestParam String username,@RequestParam int age 를 바꾸기 가능
    //ModelAttribute : 해당 객체 생성 후 객체의 프로퍼티 찾은 후 setter 호출해 파라미터의 값 바인딩함
    // 단순타입에서는 해당 어노테이션도 생략 가능
    // argument resolver 지정 타입은 생략안됨 : HttpResponseServlet같은 것
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={},age{}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }
}
