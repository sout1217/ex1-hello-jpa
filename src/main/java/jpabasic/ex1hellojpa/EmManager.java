package jpabasic.ex1hellojpa;


import jpabasic.ex1hellojpa.domain.member.Address;
import jpabasic.ex1hellojpa.domain.member.Member;
import jpabasic.ex1hellojpa.domain.member.MemberDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EmManager {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Member member = new Member();
            member.setName("martin");
            member.setAge(19);
            member.setHomeAddress(new Address("homeCity", "street2", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            List<MemberDto> result = em.createQuery("select new jpabasic.ex1hellojpa.domain.member.MemberDto(m.name, m.age) from Member m", MemberDto.class).getResultList();
            System.out.println(result.get(0).getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
