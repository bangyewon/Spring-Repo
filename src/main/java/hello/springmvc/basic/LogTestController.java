package hello.springmvc.basic;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller : 뷰 리졸버 통해서 반환
@Slf4j
@RestController // retrun이 String이면 그대로 반환 가능 https body에 바로 데이터 넣어줌
public class LogTestController {
    // 롬북 어노테이션으로 대체 가능
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        System.out.println("name = " +name);
        // 로그 레벨 정하기 가능
        log.trace("trace log ={}", name);
        log.info("info log ={}", name); // 중요한 정보 (비즈니스)
        log.debug("debug log ={}", name); // 디버그 시
        log.warn("warn log ={}", name);
        log.error("error log ={}", name); // 해당 로그 남으면 파일에서 반드시 봐야함

        return "ok";
    }
}
