package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {
     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

     @Test
     @DisplayName("빈 이름으로 조회")
     void findBeanByName(){
         MemberService memberService = ac.getBean("memberService", MemberService.class);
         // 인터페이스로 조회한다면, 인터페이스의 구현체 클래스가 대상체가 된다.
         assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
     }
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // 인터페이스로 조회한다면, 인터페이스의 구현체 클래스가 대상체가 된다.
        // 따라서 해당 인터페이스의 구현체 클래스로 getBean해도 문제가 없다.
        // 그러나 구현체 클래스로 getBean한다면 구현에 의존하는것이기 때문에 좋은 코드는 아니다..
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패하는 경우")
    void findBeanByNameX(){
//        ac.getBean("xxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("xxxx", MemberService.class));
        // 빈 이름이 컨테이너에 없다면 실패해야한다.
     }



}
