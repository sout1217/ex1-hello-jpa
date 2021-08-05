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

            Team team = new Team();
            team.setName("Rose Class");
            em.persist(team);

            Member member = new Member();
            member.setName("martin");
//            member.setCity("seoul");
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            // em.find 는 PK 를 찍어서 하는 것 이기 때문에 team 까지 조회하는 최적화 쿼리문을 만들어준다
//            Member findMember = em.find(Member.class, member.getId());

            // EAGER 로 세팅했는 데도 쿼리가 2번 나간다
            // SQL 로 그대로 번역이 된다 그대로 사용되면 Team 을 인자로 전달해주지 않았기 때문에 Member 만 조회 된다
            // 조회 된 후 EAGER 로딩이 적혀있다는 것을 확인 한 후 그 때 Team 을 조회하는 쿼리문이 나가 쿼리문이 2번 조회가 된다
            // 이럴 경우 Member List 안에 10 명이 존재한다면 10번이나 조회를 더 하는 문제가 생긴다
            List<Member> members = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
