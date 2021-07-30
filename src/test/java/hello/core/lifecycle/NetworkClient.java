package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient /** 1번 방법 implements InitializingBean, DisposableBean**/ {
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        /**
         connect();
         call("초기화 연결 메시지");
         /* *
         *
         */
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect = " + url);
    }

    public void call(String message){
        System.out.println("call : "+url+"message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close = " + url);
    }

    /**
     의존관계 주입이 끝나면 호출되는 메서드
     InitializingBean interface에 정의되어있는 메서드이다.

     @Override
    public void afterPropertiesSet() throws Exception {
         System.out.println("afterPropertiesSet() run");
         connect();
         call("초기화 연결 메시지");
    }

    /**
     해당 bean이 종료될때 호출되는 메서드
     DisposableBean interface에 정의되어있는 메서드이다.

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy() run");
        disconnect();
    }


     2번 방법
    public void init()  {
        System.out.println("init() run");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println("close() run");
        disconnect();
    }
     * 어노테이션을 활용한 3번 방법
     *  @PostConstruct어노테이션이 붙은 메서드는
     *  초기화시점에 호출되고(의존관계 주입이 끝난 후)
     *  @PreDestroy어노테이션이 붙은 메서드는
     *  종료시점에 호출된다.
     */
    @PostConstruct
    public void Annotationinit()  {
        System.out.println("init() run");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void Annotationclose() {
        System.out.println("close() run");
        disconnect();
    }
}
