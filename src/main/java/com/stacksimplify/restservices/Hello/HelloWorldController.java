package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
/*import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;*/
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//Get method: URI/helloworld
	
	
	//@RequestMapping(method=RequestMethod.GET,path="/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hellow World1";
	}
	
	@GetMapping("/helloworldbean")
	public UserDetails helloWorldBean() {
		
		return new UserDetails("roqa","khanum","asansol");
	}

}
