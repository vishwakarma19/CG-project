package frds.mgnt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import frds.mgnt.model.ProfileEntity;
import frds.mgnt.model.ProfileRepository;

@Service
@Transactional
public class ProfileService{

	@Autowired
	private ProfileRepository profileRepository;
	
	public ProfileEntity createProfile(ProfileEntity profileEntity)
	{
		 profileRepository.save(profileEntity);
		 return profileEntity;
	}

}
