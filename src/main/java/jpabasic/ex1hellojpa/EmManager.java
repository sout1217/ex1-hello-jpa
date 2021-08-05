package jpabasic.ex1hellojpa;


import jpabasic.ex1hellojpa.domain.member.Member;
import jpabasic.ex1hellojpa.domain.member.Team;

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

            Team team1 = new Team();
            team1.setName("teamA");

            Team team2 = new Team();
            team2.setName("teamB");

            em.persist(team1);
            em.persist(team2);


            Member tom = new Member();
            tom.setName("tom");
            tom.setAge(10);

            Member martin = new Member();
            martin.setName("martin");
            martin.setAge(20);
            martin.changeTeam(team1);

            em.persist(martin);
            em.persist(tom);

            em.flush();
            em.clear();

            List<Member> resultList = em.createQuery("select m from Member m inner join m.team t", Member.class).getResultList(); // Inner 조인 - inner 는 생략가능 - team 테이블에 member 가 있는 경우만
            resultList.forEach(System.err::println);
            System.err.println("------------------------------------");
            em.clear();

            List<Member> resultList2 = em.createQuery("select m from Member m left outer join m.team t", Member.class).getResultList(); // Left 조인 - outer 는 생략가능 - member 테이블 + team 테이블에 member 가 있는 경우
            resultList2.forEach(System.err::println);
            System.err.println("------------------------------------");
            em.clear();

            List<Member> resultList3 = em.createQuery("select m from Member m, Team t" , Member.class).getResultList(); // 세타 조인  - member + team
            resultList3.forEach(System.err::println);
            System.err.println("------------------------------------");
            em.clear();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
