package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.discount.DiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
    @Test
    void findAllBean(){
        /**
         * 같은 데이터타입의 bean들을 전부다 주입받아서 동적으로 bean을 선택해서 사용하는 방법
         * 사용자가 동적으로 할인정책을 선택할 수 있게끔 간단하게 로직을 구성할 수 있다.
         * 여러개의 스프링설정파일들을 동시에 컨테이너로 구성할 수 있다.
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L,"userA", Grade.VIP);
        // fixDiscountPolicy를 선택한다.
        int fixDiscountPolicy = discountService.discount(member,10000,"fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        // 할인된결과로 1000원이 나와야한다.
        assertThat(fixDiscountPolicy).isEqualTo(1000);

        // rateDiscountPolicy를 선택한다.
        int rateDiscountPolicy = discountService.discount(member,20000,"rateDiscountPolicy");

        // 할인된결과로 2000원이 나와야한다.
        assertThat(rateDiscountPolicy).isEqualTo(2000);
    }

    static class DiscountService{
        /**
         * List나 Map으로 같은 데이터타입들을 모아서 같이 주입해올 수 있다.
         */
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        // Map과 List에 전부다 주입된다.
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        /**
         discount메서드에서는 member와 가격, 할인정책을 파라미터값으로 받아서
         할인결과로 나온 가격을 반환해준다.
         **/
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }
}
