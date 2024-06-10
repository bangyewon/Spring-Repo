package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 설정정보 내용일때 붙이기
@ComponentScan //@Component 붙은 클래스들 찾아서 자동으로 빈 등록함
        (
                //이렇게 basePackages로 해버리면 지정한 경로의 파일만 컴포넌트 대상이됨 / 즉 어디서부터 찾는지 지정가능
                //여러 라이브러리 섞여있을때도 가능 ,여러 시작위치 지정가능
                basePackages = "hello.core.member",
                //basePackageClasses : 지정 클래스 가능 - 지정하지 않으면 해당 패키지를 다 뒤지게됨
                // ex_hello.core 하위파일들
                basePackageClasses = AutoAppConfig.class,
                //AppConfig는 여기서 수동으로 등록하고 있기에 자동으로 등록하면 충돌 일어남 -> 제외하자
                excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class) //뺄 것을 등록해줌
        )
public class AutoAppConfig {

//        @Autowired MemberRepository memberRepository;
//        @Autowired
//        DiscountPolicy discountPolicy;
//        @Bean
//        OrderService orderService() {
//                return new OrderServiceimpl(memberRepository,discountPolicy);
//        }

//        @Bean(name = "memoryMemberRepository")
//        MemberRepository memberRepository() {
//                return new MemoryMemberRepository();
//        }

}
