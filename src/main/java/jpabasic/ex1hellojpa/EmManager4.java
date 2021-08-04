package jpabasic.ex1hellojpa;


import jpabasic.ex1hellojpa.domain.member.Member;
import jpabasic.ex1hellojpa.domain.order.Order;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class EmManager4 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member = new Member();
            member.setName("martin");
            member.setCity("seoul");

            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            order.changeMember(member);

            em.persist(member);
            em.persist(order);

            em.flush();
            em.clear();
            System.err.println("clear------------");

            Member refMember = em.getReference(Member.class, member.getId());
            emf.getPersistenceUnitUtil().isLoaded(refMember); // false
            refMember.getName(); // 강제 초기화 1
            emf.getPersistenceUnitUtil().isLoaded(refMember); // true

            Hibernate.initialize(refMember); // 강제 초기화 2 (JPA 표준에서는 강제초기화 하는 방법이 없기 때문에 getName() 으로 초기화 해야한다)

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
