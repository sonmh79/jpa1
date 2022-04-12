package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {

        //Given
        Member member = new Member();
        member.setName("Son");

        //When
        Long savedId = memberService.join(member);

        //Then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_조회() throws Exception {

        //Given
        Member member1 = new Member();
        member1.setName("Sonny");

        Member member2 = new Member();
        member2.setName("Sonny");

        //When
        memberService.join(member1);
        memberService.join(member2);

        //Then
        fail("예외가 발생해야 한다.");
    }
}