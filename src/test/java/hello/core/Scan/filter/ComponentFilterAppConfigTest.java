package hello.core.Scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        //MyIncludeComponent인 BeanA가 빈 등록이 되는지 확인
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        //beanB는 NoSuchBeanDefinitionException이 떠야함 스캔 제외 - 스프링빈에 등록되지 않음
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );

    }
    @Configuration
    //나만의 컴포먼트 스캔 만들기
    //type = FilterType.ANNOTATION은 기본 값이라 생략해도 됨
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = MyExcludeComponent.class)
    )
    static  class ComponentFilterAppConfig {

    }
}
