package jpabook.jpashop.service;

import jpabook.jpashop.dmain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
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
        //given 이런게 주어졌을 때
        Member member = new Member();
        member.setName("kim");

        //when 이런게 실행되면
        Long saveId = memberService.join(member);

        //then 이런게 나와야 된다.
        Assert.assertEquals(member, memberRepository.findOne(saveId));
    }

    // 예외가 발생하면 return
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        // 여기에 오면 안 될때 사용한다.
        Assert.fail("예외가 발생해야 합니다.");
    }
}