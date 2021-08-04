package jpabasic.ex1hellojpa;


import jpabasic.ex1hellojpa.domain.member.Member;
import jpabasic.ex1hellojpa.domain.order.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class EmManager {

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

            Member findMember = em.getReference(Member.class, member.getId());
            System.err.println("------------");
            System.err.println(findMember.getId());
            System.err.println("------------");
            System.err.println(findMember.getName());
            System.err.println("------------");
            System.err.println(findMember.getOrders());
            System.err.println("------------");
            System.err.println(findMember.getClass());
            System.err.println(member.getClass());
            System.err.println(findMember.getClass() == member.getClass());
            System.err.println("------------");
            System.err.println(findMember instanceof Member);
            System.err.println("------------");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
