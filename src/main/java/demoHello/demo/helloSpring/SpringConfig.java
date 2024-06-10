package demoHello.demo.helloSpring;

import demoHello.demo.helloSpring.repository.JdbcMemberRepository;
import demoHello.demo.helloSpring.repository.JdbcTemplateMemberRepository;
import demoHello.demo.helloSpring.repository.MemberRepository;
import demoHello.demo.helloSpring.repository.MemoryMemberRepository;
import demoHello.demo.helloSpring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

// 자바 코드로 직접 스프링 빈 등록하기 - @Service,Repository,Autowired 이노테이션 없어도 됨

/**
 * 스프링이 configuration 읽고 스프링빈에 등록하라는 뜻
 * MemberService 읽고 해당 로직 호출해 스프링빈에 호출 후 등록
 */
@Configuration
public class SpringConfig {
    private final DataSource dataSource; //DataSource : 데베 커넥션 획득시 사용하는 객체
    // 데베 커넥션 정보 바탕으로 DataSource 생성 후 스프링 빈으로 만들어둠 -> DI 받기 가능
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JdbcMemberRepository((DataSource) em);
    }
}
