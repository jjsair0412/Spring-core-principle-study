package hello.core;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args){

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // ApplicationContext 객체를 생성해주는데, AnnotationConfigApplicationContext()안의 파라미터값으로
        // 아까 스프링 설정파일로 만들어주었던 AppConfig.class를 넣어준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // getBean하여 설정파일에 있던 bean객체 이름과 클래스를 차례대로 작성해 넣어주고,
        // 데이터타입에 맞춰 객체를 생성해준다.
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        // 생성해줫던 객체를 참조연산자를 이용해 사용한다.
        memberService.join(member);

        Member findmember = memberService.findMember(1L);
        System.out.println("new member : "+member);
        System.out.println("find member : "+findmember);
    }
}
