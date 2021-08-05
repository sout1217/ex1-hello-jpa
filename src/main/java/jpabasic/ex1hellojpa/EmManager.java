package jpabasic.ex1hellojpa;


import jpabasic.ex1hellojpa.domain.member.Address;
import jpabasic.ex1hellojpa.domain.member.Member;

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

            Member member = new Member();
            member.setName("martin");
            member.setHomeAddress(new Address("homeCity", "street2", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "street", "12000"));
            member.getAddressHistory().add(new Address("old2", "street", "13000"));

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, 1L);
            Address homeAddress = member.getHomeAddress();

            // 이 방법은 옳지 않다, 값 타입은 수정이아닌 교체를 해야한다 !
            // homeCity => newCity
            // findMember.getHomeAddress().setCity("newCity");
            findMember.setHomeAddress(new Address("newCity", homeAddress.getStreet(), homeAddress.getZipcode()));

            // String 같은 경우 지우고 새로 넣는 것 이다
            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            // list 는 비교할때 eq로 비교하기 때문에 eq hash code 가 제데로 구현되있다면 제거 된다
            findMember.getAddressHistory().remove(new Address("old1", "street", "12000"));
            findMember.getAddressHistory().add(new Address("newCity1", "street", "12000"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
