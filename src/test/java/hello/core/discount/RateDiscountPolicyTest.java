package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//RateDiscountPolicy가 실제로 10퍼센트가 할인이 되는지 테스트 하기
class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 적용되야 한다.")
    void vip_ok() {
        //g
        Member member = new Member(1L,"memberVIP", Grade.VIP);
        //w
        int discount = discountPolicy.discount(member,10000);
        //t
        assertThat(discount).isEqualTo(1000);
    }
    //실패 테스트도 반드시 만들어봐야함
    @Test
    @DisplayName("VIP아니면 할인이 적용되지 않아야 한다.")
    void vip_No() {
        //g
        Member member = new Member(2L,"memberVIP", Grade.BASIC);
        //w
        int discount = discountPolicy.discount(member,1000);
        //t
        assertThat(discount).isEqualTo(0);
    }


}