package demoHello.demo.helloSpring.repository;

import demoHello.demo.helloSpring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.*;
//회원 리포지토리 메모리 구현체
//인터페이스 상속시 implements 사용

/**
 * 동시성 문제가 고려X , 실무에서는 ConcurrentHashMap,AtomicLong 사용 고려함
 */
//@Repository // 회원 리포지토리 스프링 빈 등록
public class MemoryMemberRepository implements MemberRepository {

    //save 기능 : Map 사용해서 찾아가도록 하기 -> 후에 찾을때도 편리
    private static Map<Long,Member> store = new HashMap<>();
    //key 값 0,1,2...등으로 정해줌
    private static long sequence = 0L;
    //회원 정보 저장 메소드
    @Override
    public Member save(Member member) {
        //store에 넣기 전 member에 id값 셋팅
        member.setId(++sequence); //0에서 시작해 id정보 받게되면 1씩 증가하도록
        store.put(member.getId(),member); //이름은 넘어온 상태로 id만 store에 넣기
        return member;
    }
    //id찾기
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null가능성 있기에 해당 메소드로 감쌈(store의 id가져와서)
    }
    //name찾기
    @Override
    public Optional<Member> findByName(String name) {
        //values -> stream으로 변환 /.filter로 특정 조건 만족하는 values 거르기
        //.findAny()로 최종연산 -> null일시 Optinal로 감싼 null 반환되도록
        //람다식 - 람다 돌려서 store에서 찾으면 반환 Map에서 찾으면 반환 / 없으면 Optinal에서 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    //모든 회원정보 검색
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    //해당 작업이 끝나면 store 초기화시켜줘야함
    public void clearStore() {
        store.clear();
    }
}
