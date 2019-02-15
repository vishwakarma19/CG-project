package frds.mgnt.model;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author ej
 * @since 9/15/18 1:53 PM
 */
class CustomFriendRepositoryImpl implements CustomFriendRepository {

    private final EntityManager em;

    public CustomFriendRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public List<String> findCommonFriends(String emailAdd1, String emailAdd2) {
        final String sql = "SELECT cp.email_address FROM (SELECT fp1.id profile_id FROM friends f1 LEFT JOIN profiles fp1 ON f1.friend_id = fp1.id " +
                "LEFT JOIN profiles op1 ON f1.owner_id = op1.id WHERE op1.email_address = ?) your_friends INNER JOIN (SELECT fp2.id profile_id FROM " +
                "friends f2 LEFT JOIN profiles fp2 ON f2.friend_id = fp2.id LEFT JOIN profiles op2 ON f2.owner_id = op2.id WHERE " +
                "op2.email_address = ?) other_friends ON your_friends.profile_id = other_friends.profile_id INNER JOIN profiles cp ON " +
                "other_friends.profile_id = cp.id AND your_friends.profile_id = cp.id";
        return em.createNativeQuery(sql)
                .setParameter(1, emailAdd1)
                .setParameter(2, emailAdd2)
                .getResultList();
    }
}
