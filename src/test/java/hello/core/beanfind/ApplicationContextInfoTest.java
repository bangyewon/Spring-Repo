package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기") // spring의 모든 빈까지 출력됨(내가 지정한것 이외에도)
    void findAllBean() {
        String[] beanDefinitionNames= ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = "+ beanDefinitionName+"object = "+bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기") // spring의 모든 빈까지 출력됨(내가 지정한것 이외에도)
    void findApplicationBean() {
        String[] beanDefinitionNames= ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //getBeanDefinition : 빈 하나하나에 대한 정보 존재
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION : 내가 애플리케이션 주로 개발하기 위해서 등록한 빈들
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
              System.out.println("name = "+ beanDefinitionName+"object = "+bean);
            }
        }
    }
}
