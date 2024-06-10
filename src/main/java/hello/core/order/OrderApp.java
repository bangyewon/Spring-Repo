package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static hello.core.member.Grade.VIP;
//메인메소드로 하는 것은 좋은 방법이 아님
public class OrderApp {
     public  static void main(String[] args) {
//         AppConfig appConfig = new AppConfig();
//         MemberService memberService = appConfig.memberService();
//         OrderService orderService = appConfig.orderService();
//         MemberService memberService = new MemberServiceImpl(null);
//         OrderService orderService = new OrderServiceimpl(null,null);
         //MemberApp과 동일하게 OrderApp도 ApplicationContext 해주기
         ApplicationContext applicationContext = new
                 AnnotationConfigApplicationContext(AppConfig.class);
         MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
         OrderService orderService = applicationContext.getBean("OrderService", OrderService.class);

         Long memberId = 1L;
         Member member = new Member(memberId, "memberA", Grade.VIP);
         memberService.join(member);

         Order order = orderService.createOrder(memberId,"itemA",10000);

         System.out.println("order =" + order);

     }
}
