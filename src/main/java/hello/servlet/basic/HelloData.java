package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //lombok으로 하게되면 메소드들 애노테이션으로 가능
public class HelloData {
    private String username;
    private int age;



}
