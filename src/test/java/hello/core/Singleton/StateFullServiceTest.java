package hello.core.Singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StateFullServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFullService stateFullService1 = ac.getBean("stateFullService",StateFullService.class);
        StateFullService stateFullService2 = ac.getBean("stateFullService",StateFullService.class);

        // ThreadA: A사용자가 10000원 주문
        stateFullService1.order("userA",10000);
        // ThreadB: B사용자가 20000원 주문
        stateFullService2.order("userB",20000);

        /**
         *  ThreadA: 사용자A가 주문 금액을 조회
         *  로직대로라면 price는 10000원이 나와야 하는데, 20000원이 나온다. 왜일까?
         *  싱글톤 패턴에서는 동일한 객체를 호출해서 사용하기때문에, stateFullService1이던 stateFullService2던간에 다른 메모리에 객체가 생성되는것이 아니다.
         *  따라서 레퍼런스명이 다르다고 해서 다른것이아니라, 같은메모리를 참조하기때문에 같다고보아야 한다.
         *  그래서 stateFullService2가 order로 price값을 20000으로 변경한 순간, price는 20000으로 변경되는것이다.
         */
//        int price = stateFullService1.getPrice();
        // 20000원 출력
//        System.out.println("price : " + price);

//        Assertions.assertThat(stateFullService1.getPrice()).isEqualTo(20000);
        // stateFullService1.getPrice가 20000원과 동일하기때문에 테스트 성공...
    }

    @Test
    void statefulServiceSingletonSolution(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFullService stateFullService1 = ac.getBean("stateFullService",StateFullService.class);
        StateFullService stateFullService2 = ac.getBean("stateFullService",StateFullService.class);

        // ThreadA: A사용자가 10000원 주문
        int userAprice = stateFullService1.order("userA",10000);
        // ThreadB: B사용자가 20000원 주문
        int userBprice = stateFullService2.order("userB",20000);

        /**
         *  해당 문제를 해결한방법은. 공유되는 변수부분을 삭제하고 return으로 값을 바로 받아내면서 해결하였다.
         */

        Assertions.assertThat(userAprice).isEqualTo(10000);
        Assertions.assertThat(userBprice).isEqualTo(20000);
    }

    // 싱글톤의 주의점을 알아보기위한 컨테이너
    static class TestConfig{

        @Bean
        public StateFullService stateFullService(){
            return new StateFullService();
        }
    }
}
