package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import
        org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        //ThreadA: A사용자가 10000원 주문
//        statefulService1.order("userA",10000);
        // 이렇게 변경하면 지역변수이기에 사용자 A,B의 값이 다르게됨 -> 성공
        int userAPrice = statefulService1.order("userA",10000);
        //ThreadB: B사용자가 20000원 주문
//        statefulService1.order("userB",20000);
        int userBPrice = statefulService2.order("userA",10000);
        //ThreadA :A사용자가 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }
    static class TestConfig {

        @Bean
        public  StatefulService statefulService() {
            return  new StatefulService();
        }
    }

}