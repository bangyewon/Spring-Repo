package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    //test끝나면 초기화 하기 위해서
    void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void save() {
        //g
        Member member = new Member("hello",20);
        //w
        Member saveMember = memberRepository.save(member);
        //t
        Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }
    @Test
    void findAll() {
        //g
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //w
        List<Member> result = memberRepository.findAll();
        //t
        Assertions.assertThat(result.size()).isEqualTo(2); // 갯수 2개인가 ?
        Assertions.assertThat(result).contains(member1,member2); // 안에 들어있는 member들이 맞나 ?
    }

}