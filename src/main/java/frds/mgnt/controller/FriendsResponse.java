package frds.mgnt.controller;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
class FriendsResponse extends ResponseUtil implements Serializable {

    private static final long serialVersionUID = 5916489583685824222L;

    private final List<String> friends;

    @Builder
    public FriendsResponse(boolean success, String message, List<String> friends) {
        super(success, message);
        this.friends = friends;
    }

    public int getCount() {
        return friends == null ? 0 : friends.size();
    }
}
