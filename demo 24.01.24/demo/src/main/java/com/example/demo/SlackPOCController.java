package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlackPOCController {
	
	@Autowired
	SlackPOCService slackPOCService;
	
	@PostMapping(path ="/broadcastSlackMessages",consumes = "text/plain")
	public String postSlackMessages(@RequestBody String slackMessage)
	{
		return slackPOCService.PingMessageonSlackChannels(slackMessage);
	}

}
