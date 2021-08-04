package jpabasic.ex1hellojpa;


import jpabasic.ex1hellojpa.domain.member.Member;
import jpabasic.ex1hellojpa.domain.member.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmManager {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Team team = new Team();
            team.setName("Rose Class");
            em.persist(team);

            Member member = new Member();
            member.setName("martin");
            member.setCity("seoul");
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            System.err.println("findMember = " + findMember.getTeam().getClass()); // Proxy

            System.err.println("--------------------------");
            System.err.println(findMember.getTeam().getName()); // Proxy GET -> 강제 초기화
            System.err.println("--------------------------");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
