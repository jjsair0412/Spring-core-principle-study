package hello.core.Member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {


    MemberService service;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        service = appConfig.memberService();
    }

    @Test
    void join(){
        // given
            Member memberA = new Member(1L,"주진성",Grade.VIP);

        // when
            service.join(memberA);
            Member findMember = service.findMember(memberA.getId());

        // then
        // 같은지 검증하는 메서드
        Assertions.assertThat(memberA).isEqualTo(findMember);
    }
}
