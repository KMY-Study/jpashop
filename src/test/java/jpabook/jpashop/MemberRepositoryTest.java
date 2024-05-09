package jpabook.jpashop;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

//    @Test
//    public void $NAME$() throws Exception{
//        //given
//        $END$
//        //when
//
//        //then
//    }

    @Test
    @Transactional // 테스트 끝나고 DB rollback
//    @Rollback(value = false) // Rollback false
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");
        //when
        long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);
        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        /*
         * 한 트랜젝션내에서 영속성 컨텍스트내엔 객체가 같다고 본다.
         */
        Assertions.assertThat(findMember).isEqualTo(member);
    }


}