package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
//회원 저장소 만들기
//해당 클래스 생성해 인터페이스 상속받기
@Component //구현체들에게 붙이기 -> 빈 등록 안한 것들
public class MemoryMemberRepository implements MemberRepository{
    //저장소기에 Map 있어야함
    // 동시성 이슈로 Concurrent 해시맵을 쓰는게 효울적이긴 함
    private static Map<Long,Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(),member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
