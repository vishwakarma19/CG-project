package frds.mgnt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import frds.mgnt.model.ProfileEntity;
import frds.mgnt.service.ProfileService;
import static frds.mgnt.controller.BaseResponseUtil.success;


@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	ProfileService profileService;
	
	public ProfileController(ProfileService profileService)
	{
		this.profileService=profileService;
	}
	
	@PostMapping("/myprofile")
	public BaseResponseUtil createProfile(@RequestBody ProfileEntity profileEntity)
	{
		profileService.createProfile(profileEntity);
		//return Collections.singletonMap("success", "true");//success();
		return success();
	}
	
	@GetMapping("/test")
	public String test()
	{
		return "Hello,.......";
	}
}
