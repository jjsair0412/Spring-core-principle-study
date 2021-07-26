package hello.core.Singleton;

public class SingletonService {

    // 자기 자신을 private static으로 생성
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private으로 선언해서 외부에서 new 키워드로 SingletonService를 생성하지 못하게 막는다.
    private SingletonService(){
    }

    public void logic(){
        System.out.print("싱글톤 객체 로직 호출");
    }

}
