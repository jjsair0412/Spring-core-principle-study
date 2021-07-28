package hello.core.order;

import hello.core.AppConfig;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
            Order order = orderService.createOrder(memberId,"item1",10000);


        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
