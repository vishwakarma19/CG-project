package frds.mgnt.service;

import java.util.List;

public interface CommonFriendService {

    void addFriends(List<String> emailAddresses) throws FriendRuntimeException;

    List<String> getFriendsList(String emailAddress) throws FriendRuntimeException;

    List<String> getCommonFriends(List<String> emailAddresses) throws FriendRuntimeException;

    void subscribe(String requestorEmail, String targetEmail) throws FriendRuntimeException;

    void block(String requestorEmail, String targetEmail) throws FriendRuntimeException;

    List<String> notify(String sender, String text) throws FriendRuntimeException;
}
