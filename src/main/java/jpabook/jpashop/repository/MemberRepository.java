package jpabook.jpashop.repository;

import jpabook.jpashop.dmain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor // 스프링 data jpa 가 em을 주입할 때 @Autowired 가 동작하도록 지원 해준다.
public class MemberRepository {

    // 스프링이 EntityManager 를 생성해서 자동으로 주입해 준다.
    //@PersistenceContext
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
