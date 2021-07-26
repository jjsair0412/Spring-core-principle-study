package hello.core;

import hello.core.Member.MemberRepository;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import hello.core.Member.MemoryMemberRepository;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Configuration 어노테이션을 달아준 클래스는, 스프링 설정파일이 된다.
public class AppConfig {


    @Bean
    // Bean 어노테이션을 작성해서 bean객체로 만들어준다.
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     @Bean memberService -> new MemoryMemberRepository()
     @Bean orderService -> new MemoryMemberRepository()
     동일한 객체를 두번 참조했기때문에 싱글톤이 깨진다 ???

     예상 :
     call AppConfig.memberService
     call AppConfig.memberRepository
     call AppConfig.memberRepository
     call AppConfig.orderService
     call AppConfig.memberRepository

     결과 :
     @Configuration 어노테이션을 적용했기때문에 기존의 AppConfig.class 상속받는 임의의
     클래스에서 싱글톤을 보장해주기 때문에 이러한 결과가 생성된다.
     call AppConfig.orderService
     call AppConfig.memberRepository
     call AppConfig.memberService
    **/

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
}
