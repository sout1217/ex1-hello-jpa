package jpabasic.ex1hellojpa;


import jpabasic.ex1hellojpa.domain.member.Member;
import jpabasic.ex1hellojpa.domain.order.Order;

import javax.persistence.*;
import javax.persistence.spi.PersistenceUnitInfo;
import java.time.LocalDateTime;

public class EmManager3 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member = new Member();
            member.setName("martin");
            member.setCity("seoul");

            em.persist(member);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember = " + refMember.getClass()); // Proxy


            // 관리 안하거나
            em.detach(refMember);

            // detach 또는 close 를 하지 않은 경우에는 쿼리가 정상적으로 나가지만 한 경우는 안나간다
            refMember.getName();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
