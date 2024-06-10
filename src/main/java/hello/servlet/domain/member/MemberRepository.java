package hello.servlet.domain.member;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long,Member> store = new HashMap<>();
    private  static long sequence = 0L;

    //싱글톤으로 만들기
    private  static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }
    //싱글톤으로 만들땐 private로 생성자 막아야함
    private MemberRepository() {
    }
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }
    public Member findById(Long id) {
        return store.get(id);
    }
    public List<Member> findAll() {
        //밖에서 조작해도 store에 있는 values를 건들고 싶지 않기에
        return new ArrayList<>(store.values()); // store자체 보호위해
    }
    public void clearStore() {
        store.clear();
    }
}
