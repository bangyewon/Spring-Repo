package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 주문 서비스 -> 할인 관련 구현하기
//해당 인터페이스는 OrderService 의존 / MemberRepository,DiscountPolicy 참조 하고 있음
@Component
//@RequiredArgsConstructor // 롬북 애노테이션 : final이 붙은 것을 파라미터로 생성자를 만들어줌
public class OrderServiceimpl implements OrderService {
    //고정 할인 정책의 memberRepository -> member 확인해야하기에
//    private final MemberRepository memberRepository = new MemoryMemberRepository();


    //고정 할인 정책
//    private final DiscountPolicy discuntPolicy = new FixDiscountPolicy();
    //바뀐 할인 정책으로 고치기
//        private final DiscountPolicy discuntPolicy = new RateDiscountPolicy();
    //하지만 의존성 문제로 dip위반하기에 다시 바꿈
//    @Autowired
    private MemberRepository memberRepository; //필드주입 의존성 주입
//    @Autowired
    private DiscountPolicy discountPolicy; // 하지만 에러나요 ..-> 구체 객체가 없기때문에

    //수정자 주입 방법 -> setter 사용
    @Autowired // 해당 애노테이션 없으면 주입 안됨
    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository =" + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired(required = false)// 주입대상없이도 동작하게 하기위함
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy =" + discountPolicy);
        this.discountPolicy = discountPolicy;
    } // 해당 수정자 메소드가있으면 생성자에 의존관계 안해도됨

    //생성자 만들어주기
    //AppConfig와 연결해 생성자 주입 완료 시키기
    @Autowired // 생성자 의존관계 주입 - 빈등록하게되면 주입도 같이 일어남
    //생성자에는 왠만하면 값 다 넣어주기
    public OrderServiceimpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository =" + memberRepository);
//        System.out.println("discountPolicy =" + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Autowired // 메서드 주입 -> 일반적으로 잘 사용하지 않음
    public  void init(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    /**
     *주문 생성 요청 오면
     * 1. 회원 정보 먼저 조회해 grade 판단
     * 2. 할인 정책에 회원 넘기기 - 등급만 넘겨도 되지만 확장성 등 고려해 member 통채로 넘김
     * 3. 최종적으로 할인된 가격 받기 : discountPrice
     * 4. 리턴 값으로 할인된 주문 값 받게됨
     */
    @Override
    public Order createOrder(Long memberId,String itemName,int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
