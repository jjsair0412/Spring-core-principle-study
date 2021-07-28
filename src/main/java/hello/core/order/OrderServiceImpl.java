package hello.core.order;

import hello.core.Member.Member;
import hello.core.Member.MemberRepository;
import hello.core.Member.MemoryMemberRepository;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// RequiredArgsConstructor를 활용해서 생성자주입 코드를 엄청 간략화할수 있다
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     *
     * 수정자 주입 예제
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired // 수정자 주입
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired // 수정자 주입
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
     **/

    /**
     * 필드 주입 예제
     *    @Autowired private final DiscountPolicy discountPolicy;
     *    @Autowired private final MemberRepository memberRepository;
     */

    /**
        생성자 주입 예제
     **/
   private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;
    /**

    @Autowired // 생성자 주입
     * 같은 타입이 여러가지존재할경우 이름을보고 매칭도 해준다.
     * rateDiscountPolicy와 RateDiscountPolicy.class의 bean 이름은 rateDiscountPolicy이기 때문에 매칭성공

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy){
        this.discountPolicy = rateDiscountPolicy;
        this.memberRepository = memberRepository;
    }
     */

    /**
     @Qualifier를 파라미터안에 넣어주면,
     여기 이름을보고 @Qualifier 추가 구분자가 붙은클래스 이름에서 보고 같은걸 찾아서 매칭해준다.
     근데 @Qualifier에 설정한 이름을 찾지못하면 @Qualifier에 붙은 이름을 가진 bean을 추가로 찾는다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }
    **/
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }
    /**
     * 일반 메서드 주입
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy= discountPolicy;
    }
     **/


    /**
     * 롬복을 활용해서 생성자 자동주입을 이 두줄로 끝내버린다.

     private final DiscountPolicy discountPolicy;
     private final MemberRepository memberRepository;

     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrcie = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName, itemPrice, discountPrcie);
    }

    // 테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
