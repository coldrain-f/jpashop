package jpabook.jpashop.service;

import jpabook.jpashop.dmain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 조회는 readOnly = true 를 거는것이 성능 향상이 된다.
@Transactional(readOnly = true)
@RequiredArgsConstructor // final 이 있는 필드들을 가지고 생성자를 만들어준다. ( 권장 )
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional // 조회가 아니므로 @Transaction 을 걸어준다.
    public Long join(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}