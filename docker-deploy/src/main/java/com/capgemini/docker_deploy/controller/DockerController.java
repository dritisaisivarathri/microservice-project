package com.capgemini.docker_deploy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {
	
	@GetMapping("/a")
	public String get() {
		return "Docker Prac";
	}

}
