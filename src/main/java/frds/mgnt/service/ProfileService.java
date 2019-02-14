package frds.mgnt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import frds.mgnt.dao.ProfileRepository;
import frds.mgnt.model.ProfileEntity;

@Service
@Transactional
public class ProfileService{

	@Autowired
	private ProfileRepository profileRepository;
	
	public ProfileEntity createProfile(ProfileEntity profileEntity)
	{
		return profileRepository.save(profileEntity);
	}

}
