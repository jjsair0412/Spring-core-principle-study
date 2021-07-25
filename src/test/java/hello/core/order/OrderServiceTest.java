package hello.core.order;

import hello.core.AppConfig;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void BeforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void crateorder(){
        // given
            Long memberId = 1L;
            Member member = new Member(memberId,"member1", Grade.VIP);
            memberService.join(member);

        // when
            Order order = orderService.createOrder(memberId,"item1",100000);


        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
