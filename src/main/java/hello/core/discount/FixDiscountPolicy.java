package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
//정액 할인정책 구현체
public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000; //천원 할인
    @Override
    public int discount(Member member, int price) {
        //VIP이면 할인
        if(member.getGrade() == Grade.VIP) { //enum type은 ==을 통해 비교함
            return discountFixAmount;
        }else {
            return 0;
        }
    }
}
