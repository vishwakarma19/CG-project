package frds.mgnt.service;

import java.util.List;

public class CommonFriendServiceImpl implements CommonFriendService {

	@Override
	public void addFriends(List<String> emailAddresses) throws FriendRuntimeException {
		
	}

	@Override
	public List<String> getFriendsList(String emailAddress) throws FriendRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCommonFriends(List<String> emailAddresses) throws FriendRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void subscribe(String requestorEmail, String targetEmail) throws FriendRuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void block(String requestorEmail, String targetEmail) throws FriendRuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> notify(String sender, String text) throws FriendRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
