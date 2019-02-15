package frds.mgnt.controller;

import static frds.mgnt.controller.ResponseUtil.success;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import frds.mgnt.service.FriendService;


@RestController
@RequestMapping("/friends")
public class FriendRestController {

    private final FriendService friendService;

    public FriendRestController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/add")
    public ResponseUtil addFriends(@Valid @RequestBody FriendsRequest request) {
        friendService.addFriends(request.getFriends());
        return success();
    }

    @GetMapping("/list")
    public FriendsResponse getFriends(@Valid @RequestBody GetFriendsRequest request) {
        List<String> friends = friendService.getFriendsList(request.getEmail());
        return FriendsResponse.builder()
                .success(true)
                .friends(friends)
                .build();
    }

    @GetMapping("/common")
    public FriendsResponse getCommonFriends(@Valid @RequestBody FriendsRequest request) {
        List<String> commonFriends = friendService.getCommonFriends(request.getFriends());
        return FriendsResponse.builder()
                .success(true)
                .friends(commonFriends)
                .build();
    }

    @PostMapping("/subscribe")
    public ResponseUtil subscribe(@Valid @RequestBody SubscriptionRequest request) {
        friendService.subscribe(request.getRequestor(), request.getTarget());
        return success();
    }

    @PostMapping("/block")
    public ResponseUtil block(@Valid @RequestBody BlockRequest request) {
        friendService.block(request.getRequestor(), request.getTarget());
        return success();
    }

    @PostMapping("/notify")
    public FriendsResponse notify(@Valid @RequestBody NotificationRequest request) {
        List<String> notifiable = friendService.notify(request.getSender(), request.getText());
        return FriendsResponse.builder()
                .success(true)
                .friends(notifiable)
                .build();
    }
}
