package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonAndPrototypeTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext singletonac = new AnnotationConfigApplicationContext(SingletondBean.class);

        SingletondBean Singletonbean1 = singletonac.getBean(SingletondBean.class);
        SingletondBean Singletonbean2 = singletonac.getBean(SingletondBean.class);

        System.out.println("Singletonbean1 : "+ Singletonbean1);
        System.out.println("Singletonbean2 : "+ Singletonbean2);

        /**
         * 싱글톤 빈이기때문에 당연히 같은 메모리를 가지고있다.
         */
        assertThat(Singletonbean1).isSameAs(Singletonbean2);
        singletonac.close();
    }

    @Test
    void prototypeTest(){
        /**
         * 프로토타입 빈이기 때문에 다른 메모리를 가지고 있으며
         * 만들어진 bean의 관리는 클라이언트의책임이기 때문에
         * 컨테이너를 종료시키는 close구문이 실행되서 컨테이너가 삭제되더라도
         * bean이 종료되고 호출되는 close 메서드가 실행되지 않는다.
         **/
        AnnotationConfigApplicationContext prototypeac2 = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find Prototypebean1");
        PrototypeBean Prototypebean1 = prototypeac2.getBean(PrototypeBean.class);
        System.out.println("find Prototypebean2");
        PrototypeBean Prototypebean2 = prototypeac2.getBean(PrototypeBean.class);

        System.out.println("Prototypebean1 : "+ Prototypebean1);
        System.out.println("Prototypebean2 : "+ Prototypebean2);

        /**
         * 만약 종료메서드를 호출하고 싶다면,
         * 프로토타입 빈을 호출한 클라이언트가
         * 종료를 해주어야 한다.
         */
        Prototypebean1.close();
        Prototypebean2.close();

        assertThat(Prototypebean1).isNotSameAs(Prototypebean2);
        prototypeac2.close();
    }

    /**
     * 실제로는 Scope에 싱글톤이라고 작성하지 않아도 기본적으로 싱글톤빈을 생성하지만
     * 실습을 위해 작성해준다.
     */
    @Scope("singleton")
    static class SingletondBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void close(){
            System.out.println("SingletonBean.close");
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("Prototype.init");
        }

        @PreDestroy
        public void close(){
            System.out.println("Prototype.close");
        }
    }
}

