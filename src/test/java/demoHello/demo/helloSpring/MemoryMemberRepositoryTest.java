package demoHello.demo.helloSpring;

import demoHello.demo.helloSpring.domain.Member;
import demoHello.demo.helloSpring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 무조건 테스트는 하나하나가 독립적으로 실행되야함
 * 의존적이면 절대 안됨!
 */
//회원 리포지토리 메모리 구현체 테스트
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 테스트 메서드가 실행된 후에 실행되는 메서드
    //테스트 후에 사용된 테스트 메소드들 초기화 해주는 역할
    //반드시 테스트 후엔 초기화 해야한다 !!
    public void afterEach() {
        repository.clearStore();
    }
    @Test // 테스트 시에는 붙여주기
    public void save() {
        //G
        Member member = new Member();
        member.setName("spring");
        //W
        repository.save(member);
        //T
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }
    @Test
    public  void findByName() {
        //g - 주어진 상황
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //w - 실행
        /**
         * MemoryMemberRepository에서 findByName 메소드 사용해"spring1"회원찾음
         * Optinal로 감싸져 있는 반환타입이기에 .Get()메소드 통해 실제 회원 객체 가져옴
         */
        Member result = repository.findByName("spring1").get();
        //t - 검증
        /**
         * assertThat()사용해 실행부분의 result 검증함 : assertThat() :검증테스트에서 쓰이는 메소드로 값이 맞지 않으면 실패시킴
         * member1과 비교해 result값이 동일하면 통과시킴
         */
        assertThat(result).isEqualTo(member1);
    }
    //전체회원 조회
    @Test
    public  void findAll() {
        //g
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //w
        List<Member> result = repository.findAll(); // 리스트로 findAll() 메소드 통해 실행 시키기
        //t
        assertThat(result.size()).isEqualTo(2); //member1,2이므로 2개 즉 리스트 size가 2개여야함으로 asssertThat으로 비교
    }
}
