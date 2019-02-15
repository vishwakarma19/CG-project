package frds.mgnt.model;

import java.util.List;

/**
 * @author ej
 * @since 9/15/18 1:51 PM
 */
public interface CustomFriendRepository {

    List<String> findCommonFriends(String emailAdd1, String emailAdd2);
}
