package com.example.securingweb;
	
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.RestController;

	@RestController
	public class SampleController {

		@GetMapping("/")
		public String home() {
			return "Home Page";
		}
		
		@GetMapping("/user")
		public String user() {
			return "User Page";
		}
		
		@GetMapping("/admin")
		public String admin() {
			return "Admin Page";
		}
	}

