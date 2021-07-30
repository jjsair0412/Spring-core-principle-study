package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        // 둘다 컴포넌트 스캔을 해줘야 bean등록이 되어서 자동주입이 된다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        /**
         * 실패했을 경우의 test코드
        assertThat(count2).isEqualTo(2);**/

        /**
         * ObjectProvider를 사용해서 항상 새로운 bean을 반환하게끔 했다.
         */
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{
        // 생성시점에 주입되기 때문에 계속 같은 변수를 사용한다.
        private final PrototypeBean prototypeBean;

        /**
         * ObjectProvider는 <>안에 들어가있는 bean을 찾아주기만 한다.
         * ObjectFactory를 사용해도 무관하다.
         @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
         */

        /**
         * 3번쨰 방법인 Provider를 활용한 방법.
         * javax.provider를 사용한다.
         * ObjectProvider와 차이점은 getObject()였는데
         * 얘는 get으로 컨테이너에서 bean을 찾아와준다.
         */
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean = prototypeBean;
        }

        public int logic(){
            /**
            PrototypeBean beanProviderObject = prototypeBeanProvider.getObject();

            prototypeBean.addCount();
            return prototypeBean.getCount();
             **/
            PrototypeBean beanProviderObject = prototypeBeanProvider.get();

            beanProviderObject.addCount();
            return beanProviderObject.getCount();
        }
    }


    @Scope("prototype")
    static class  PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("prototypeBean.destroy");
        }
    }
}
