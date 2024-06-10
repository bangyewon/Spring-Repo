package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
//이렇게되면 SameBeanConfig.class의 등록된 빈만 조회되게 됨
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류 발생한다.") // 예외발생해야함
    void findBeanByTypeDuplicate() {
        MemberRepository bean = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 됨")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String,MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key =" + key+"value = " + beansOfType.get(key));//key - value로 출력됨
        }
        System.out.println("beansOfType ="+beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration // class 내부에 static class 제작하면 내부에서만 쓰겠다는 말
    static  class SameBeanConfig {
        //파라미터가 다를 수도 있기에 빈 이름 다르고 객체타입(인스턴스 타입)이 같을 수 있음
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
