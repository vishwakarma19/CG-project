package frds.mgnt.model;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends BaseRepository<ProfileEntity> {

    Optional<ProfileEntity> findByEmailAddress(String emailAddress);
}
