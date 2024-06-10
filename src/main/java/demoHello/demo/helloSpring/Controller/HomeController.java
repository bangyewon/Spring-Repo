package demoHello.demo.helloSpring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//localhost:8080 들어오면 바로 호출되는 것 home.html
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
