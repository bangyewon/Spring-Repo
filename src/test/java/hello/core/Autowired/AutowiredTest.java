package hello.core.Autowired;

import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Member;
import java.util.Optional;

public class AutowiredTest {
    //옵션처리 테스트
    @Test
    void  AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        //getBean(...);
    }

    static class TestBean {
        @Autowired(required = false)
        public  void setNoBean1(Member noBean1) {
            System.out.println("noBean1 =" +noBean1);
            //대상 없기에 메서드 자체가 호출 안됨 -> 에러남
        }
        @Autowired
        public  void setNoBean2(@Nullable Member noBean2) {
            //호출은 됨
            System.out.println("noBean2 =" +noBean2);
        }
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            //값이 없으면 Optional로 감싸서 나옴
            System.out.println("noBean3 = "+ noBean3);
        }
    }
}
