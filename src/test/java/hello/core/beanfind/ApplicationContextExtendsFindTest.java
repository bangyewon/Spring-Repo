package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
//실제 개발할땐 애플리케이션에서 빈 조회를 할 기회는 없음
// 부모타입 조회할때 어디까지 조회가 되는지 알아야 자동 의존관계 주입시에 도움이 됨
public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면,중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면,중복 오류가 발생한다.")
    void findBeanByParentTypeBeanName() {
        //실제 구현객체는 rateDiscountPolicy 가 나올것임
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }
    @Test
    //안좋은 방법
    @DisplayName("특정 하위 타입으로 조회")
    void  findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }
    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void  findAllBeanByParentType() {
        Map<String,DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = "+key+"value ="+beansOfType.get(key)); // 앞으로는 출력문을 남기진 말자
        }
    }
    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    //spring  bean에 등록된 모든 애들이 튀어나옴 -> 모든 상위 객체는 object이기에
    void  findAllBeanByObjectType() {
        Map<String,DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = "+key+"value ="+beansOfType.get(key)); // 앞으로는 출력문을 남기진 말자
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        //개발이나 설계할때 추상적으로 해도 괜찮음 DiscountPolicy
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
