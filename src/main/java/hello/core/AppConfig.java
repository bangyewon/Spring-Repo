package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceimpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정 정보 적어줌
public class AppConfig {
    /**
     * 이전에는 MemberServiceImp이 MemoryMemberRepository를 지정해줌
     * 이것을 AppConfig에서 환경설정을 모두 하는 것으로 의존성 바꿔야함
     */
    //AppConfig통해 memberService를 불러서 쓰게됨 -> MemberServiceImpl의 구현체 객체가 생성됨
    @Bean //Bean 적어주면 이것들이 모두 스프링 컨테이너에 등록됨
    public MemberService memberService() {
        // 결과적으로 "call AppConfig.memberRepository"가 3번 호출됨 -> 하지만 메서드가 한번 호출됨 (싱글톤 보장됨)
        System.out.println("call AppConfig.memberRepository");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    //OrderService에서도 생성자 주입 할것임 -> OrderServiceimpl에서

    /**
     * 즉, AppConfig의 OrderService에서 해당 인자들이 넘어가 OrderServiceimpl의 final로 생성됨
     * OrderServiceimpl의 생성자에서 this를 통해 할당 완료됨
     * <생성자 주입 완료> -> DIP 지키기 완료됨 (인터페이스에만 의존 하게 됐음으로)
     */
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.memberRepository");
//        return new OrderServiceimpl(memberRepository(),new FixDiscountPolicy());
        return null;
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
    //할인정책 변경 되더라도 appConfig에서만 변경하면됨
    return new RateDiscountPolicy();
    }
}
