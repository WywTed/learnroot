package com.huhusky.server.d;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerdController {

	@GetMapping("/dinfo")
	public String dinfo() {
		return "provider-server-d";
	}
}
