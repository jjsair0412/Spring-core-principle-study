package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 *
 * 로그를 출력하기 위한 MyLogger 클래스
 * 해당 bean이 생성되는 시점에 자동으로 uuid가 생성되고 저장된다.
 * 이 빈은 Http 요청당 하나씩 생성되므로 UUID를 저장해두면
 * 다른 Http 요청과 구분할 수 있다.
 *
 * 이 빈이 소멸되는 시점에 @PostDestroy를 사용해서 종료 메시지를 남긴다.
 *
 * 해당 bean의 scope범위는 request이기때문에,
 * ( 클라이언트 요청 )request가 들어와서 나가는 범위까지 쓸 수 있다.
 * 그니까 이 사이에서 주입시켜야 한다.
 */

//@Scope(value = "request")
@Component
/**
 * 프록시를 활용한 방법
 * proxyMode로 대상을 정해준다.
 * 적용 대상이 인터페이스가 아닌 클래스면 TARGET_CLASS 를 선택
 * 적용 대상이 인터페이스면 INTERFACES 를 선택
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestURL+"]"+message);
    }

    @PostConstruct
    public void init(){
       uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create: "+this);
    }
    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean create: "+this);
    }
}
