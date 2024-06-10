package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// MemberService 구현체
//Impl이라고 붙인 이유 : 구현체가 하나만 있을때 인터페이스 명 뒤에 Impl이라고 붙이는 관례
@Component // Component 보고 자동으로 스프링 빈 등록시켜줌 -> ComponentScan
public class MemberServiceImpl implements MemberService {
    //구현객체 선택해 줘야함
//    private  final MemberRepository memberRepository = new MemoryMemberRepository();

    //관심사 분리 위해 MemberServiceImpl의 생성자를 통해서 memoryMemberRepository에 어떤게 들어갈지 생각하는 것
    /**
     * AppConfig를 통해 MemberServiceImpl의 구현체 객체가 생성되 불러옴->
     * 해당 MemberServiceImpl로 와서 memberRepository에 할당이 되게됨
     * 하지만 현재 이코드에선 MemoryMemberRepository에 대한 코드가 없음 -> 밖에서 생성해 넣어줄것임 (생성자 주입)
     * 그렇기에 해당 코드 없어도 가능함
     */
    private  final MemberRepository memberRepository;
    @Autowired // 자동 의존관계 주입 위해서 생성자에 넣기
    //ac.getBean(MemberRepository.class)와 동일한 기능
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 다형성에 의해서 join을 호출하게 되면 MemoryMemberRepository의 인터페이스가 아닌 해당  MemoryMemberRepository에 있는 save 호출됨
     * 오버라이드 한 것이 호출되는 것
     */
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    //test용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
