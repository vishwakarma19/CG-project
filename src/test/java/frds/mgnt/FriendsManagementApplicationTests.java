package frds.mgnt;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import frds.mgnt.model.FriendRepository;
import frds.mgnt.service.FriendService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendsManagementApplicationTests {
	  @Autowired
	    private FriendRepository repository;

	    @Autowired
	    private FriendService service;

	    @Test
	    public void contextLoads() {
	        assertNotNull(repository);
	        assertNotNull(service);
	    }

}

