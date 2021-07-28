package hello.core.Member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 다형성에 의해서 MemberRepository 인터페이스를 재정의한
    // MemoryMemberRepository에 있는 메서드가 실행되게 된다.
    private MemberRepository memberRepository;

    @Autowired // 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
