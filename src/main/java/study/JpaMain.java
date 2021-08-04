//package study;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//import static javax.persistence.GenerationType.IDENTITY;
//
//public class JpaMain {
//
//    public static void main(String[] args) {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try {
//
//            // 비영속
//            TestMember member = new TestMember();
//            member.setId(10L);
//            member.setName("Iron");
//
//            // 영속 (DB 에 저장하는 것이 아닌 영속성 컨텍스트에 저장하는 것)
//            em.persist(member);
//
//            // 준 영속
//            em.detach(member);
//
//
//
//            // 커밋을 하는 경우 영속성 컨텍스트에서 DB 로 쿼리문을 보낸다
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        emf.close();
//    }
//}
//
//
//@Entity
//@Getter
//@Setter
//class TestMember {
//
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    private Long id;
//    private String name;
//
//
//}