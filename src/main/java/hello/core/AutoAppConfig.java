package hello.core;

import hello.core.Member.MemberRepository;
import hello.core.Member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /**
        탐색할 하위클래스나 패키지를 지정해주지 않는다면
        @ComponentScan이 붙은 설정정보 패키지의 위치가 시작이 된다.
        따라서 ComponentScan이 붙은 클래스를 프로젝트의 최 상단에 놓는다면 굳이 설정해주지않는다 하더라도
        모든 패키지,클래스를 탐색한다.
         **/
        basePackages = "hello.core.Member",
        /**
         * 이렇게 basePackages를 설정해주면, 설정해준 패키지의 하위 패키지들만 찾는다.
        */
        basePackageClasses = AutoAppConfig.class,
        /**
         *  basePackageClasses을 설정해주면, 설정해준 클래스의 하위 클래스들을 탐색한다.
        */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        /**
        exculdeFilters를 사용해서 컴포넌트 자동스캔이 될때 제외되는 클래스를 선택할 수 있다.
        컴포넌트 스캔에서는 @Configuration 어노테이션이 붙은 클래스도 컴포넌트 스캔의 대상이 되어 bean 객체로 만들어버린다.
        이전에 만들어두었던 AppConfig.class는 bean객체를 수동으로 만드는 스프링 설정파일이기 때문에 제외시킨다.
        일반적으론 제외하지는 않는다.
         **/
)
public class AutoAppConfig {

        /**
        @Bean(name = "memoryMemberRepository")
        MemberRepository memberRepository(){
                return new MemoryMemberRepository();
        }
         컴포넌트 스캔햇을때 bean이름이 같아서 충돌을 일으키는 경우
        **/

}
