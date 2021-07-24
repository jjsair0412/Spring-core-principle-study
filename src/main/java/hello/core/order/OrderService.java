package hello.core.order;

public interface OrderService {
    // 할인된 가격을 반환해주는 service
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
