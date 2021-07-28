package hello.core.discount;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
//@Qualifier 이름 설정
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
