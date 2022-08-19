package hellojpa;

import net.bytebuddy.asm.MemberRemoval;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            Address address = new Address("homecity", "Strret",
                    "10");
            member.setHomeaddress(address);
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "Strret",
                    "10"));
            member.getAddressHistory().add(new AddressEntity("old2", "Strret",
                    "10"));
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===================== start");
            Member findMeber = em.find(Member.class, member.getId());

//            List<Address> addressHistory = findMeber.getAddressHistory();
//
//            for (Address address1 : addressHistory) {
//                System.out.println("address1 = " + address1.getCity());
//            }

            findMeber.getFavoriteFoods().remove("치킨");
            findMeber.getFavoriteFoods().add("한식");
            System.out.println("===================== ");

            Set<String> favoriteFoods = findMeber.getFavoriteFoods();

            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }
            //  member.getHomeaddress().setCity

            //homecity -> newcity로변경하고싶을떄
//이렇게 하면사이드 이팩트 발생 할 수이ㅣㅆ따  findMeber.getHomeaddress().setcoty("newcity")

            Address a = findMeber.getHomeaddress();
            findMeber.setHomeaddress(new Address("newCIty", a.getStreet(), a.getZipcode()));

            findMeber.getAddressHistory().remove(new Address("old1", "Strret",
                    "10"));
//            findMeber.getAddressHistory().add(new Address("newcity1", "Strret",
//                    "10"));
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
