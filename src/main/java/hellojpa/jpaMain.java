package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            List<Member> result = em.createQuery("select m from Member  as m ", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }
            //비영속
            Member member = new Member();
            member.setId(101l);
            member.setName("HelloA");

            //영속
            System.out.println("brfor" );
            em.persist(member);
            System.out.println("after");
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getId() = " + findMember.getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
