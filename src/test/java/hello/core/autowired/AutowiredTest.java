package hello.core.autowired;

import hello.core.Member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        @Autowired(required = false)
        /**
         * member는 컨테이너에 관리되고 있지 않은 객체이다.
         * Autowired로 bean객체를 자동주입하려하는데,
         * 컨테이너에 없는 값이 파라미터로있을경우
         * 오류를 발생시키지 않는다.
         *
         * 자동주입할 대상이 없다면 해당 메서드가 아예호출되지 않는다.
         *
         * 그니까 들어갈 값이 없어도 오류를 발생시키지 않는다.
         * required의 디폴트인 true는 오류를 뱉는다.
         */
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }
        
        @Autowired
        /**
         * @Nullable 어노테이션을 파라미터에 넣어줫을경우
         * 컨테이너에 관리되고있지 않은 값이 파라미터로 들어왔을때
         *
         * 의존관계자동주입할 값이 없다면
         * 얘는 호출은 되는데 null로 값이 설정된다.
         */
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        /**
         * Optional로 감싸서 파라미터로 넣어줫을때 자동주입시키면 결과는
         * Optional.empty 가 입력된다.
         */
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
