package hello.core.discount;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L,"memberA", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member,10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("Vip가 아니면 10%할인이 되면 안됀다.")
    void vip_x(){
        //given
        Member memberB = new Member(1L,"memberA", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(memberB,10000);

        //then
        assertThat(discount).isEqualTo(0);
    }

}