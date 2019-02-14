package frds.mgnt.dao;


import frds.mgnt.model.ProfileEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository  extends JpaRepository<ProfileEntity, Integer> {
 //   Optional<ProfileEntity> findByEmailAddress(String emailAddress);
    
}
