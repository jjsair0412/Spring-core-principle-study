package hello.core.Singleton;

public class StateFullService {

    /**
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name : "+ name + "price = " + price);
        this.price = price; // 이곳이 문제이다.
    }

    public int getPrice(){
        return price;
    }

     여기까지 문제가 있었던 코드

     * 해결방법
     * 가격을 return받아서 지역변수로 저장하자.
     */

    public int order(String name, int price){
        System.out.println("name : "+ name + "price = " + price);
        return price;
    }


}
