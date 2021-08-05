package jpabasic.ex1hellojpa;


import jpabasic.ex1hellojpa.domain.member.Address;
import jpabasic.ex1hellojpa.domain.member.AddressHistory;
import jpabasic.ex1hellojpa.domain.member.Member;

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
            member.setHomeAddress(new Address("homeCity", "street2", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressHistory(new Address("osan", "street", "10000")));
            member.getAddressHistory().add(new AddressHistory(new Address("dongtan", "street", "20000")));
            member.getAddressHistory().add(new AddressHistory(new Address("busan", "street", "30000")));

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, 1L);


            // update 쿼리가 나가는 것은 @OneToMany 단방향 매핑이라 어쩔 수 없다
            List<AddressHistory> addressHistory = findMember.getAddressHistory();

            addressHistory.remove(1);
            addressHistory.add(new AddressHistory(new Address("suwon", "street", "40000")));


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
