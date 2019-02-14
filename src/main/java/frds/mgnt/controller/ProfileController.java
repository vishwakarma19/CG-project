package frds.mgnt.controller;

import javax.ws.rs.GET;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import frds.mgnt.model.ProfileEntity;
import frds.mgnt.service.ProfileService;
import static frds.mgnt.controller.BaseResponseUtil.success;
import static frds.mgnt.controller.BaseResponseUtil.failed;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	ProfileService profileService;
	
	public ProfileController(ProfileService profileService)
	{
		this.profileService=profileService;
	}
	
	@GET
	public BaseResponseUtil createProfile(@RequestBody ProfileEntity profileEntity)
	{
		profileService.createProfile(profileEntity);
		return success();
	}
}
