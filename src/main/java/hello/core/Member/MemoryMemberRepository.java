package hello.core.Member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 메모리에서만 저장되는 Member
@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
