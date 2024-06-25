package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletRequest response,
                          HttpMethod httpMethod,
                          Locale locale, // locale 조회
                          // MultiValueMap : 하나의 키에 여러 값 받기 가능 - 하나의 key꺼내면 리스트로 반환됨
                          @RequestHeader MultiValueMap<String,String> headerMap, // 모든 http헤더를 multivaluemap 방식으로 조회
                          @RequestHeader("host") String host, // 특정 헤더 조회
                          @CookieValue(value = "myCookie",required = false) String cookie // 특정 쿠키 조회
    ) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
