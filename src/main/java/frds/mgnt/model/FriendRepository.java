package frds.mgnt.model;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends BaseRepository<FriendEntity>, CustomFriendRepository {

    List<FriendEntity> findByOwnerAndStateNot(ProfileEntity owner, FriendState state);

    List<FriendEntity> findByOwnerAndFlagAndState(ProfileEntity owner, boolean flag, FriendState state);

    Optional<FriendEntity> findByOwnerAndFriend(ProfileEntity owner, ProfileEntity friend);

    List<FriendEntity> findByFlagAndState(boolean flag, FriendState state);
}
