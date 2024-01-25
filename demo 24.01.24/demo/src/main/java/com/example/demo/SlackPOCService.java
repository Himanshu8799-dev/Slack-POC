package com.example.demo;

import java.io.IOException;
import java.util.Collections;

import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

@Service
public class SlackPOCService {

	private static final String NEW_LINE = "\n";
	private static String webhook_channel1 = "https://hooks.slack.com/services/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";
	private static String webhook_channel2 = "https://hooks.slack.com/services/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";
	private static String channel1 = "slack-automation-poc";
	private static String channel2 = "slack-automation-poc-2";

	public String PingMessageonSlackChannels(String slackMessage) {
		sendMessageToSlack(slackMessage, channel1, webhook_channel1);
		sendMessageToSlack(slackMessage, channel2, webhook_channel2);
		return "Success send slack !";
	}

	public static void sendMessageToSlack(String message, String channel, String webhookURL) {

		StringBuilder messageBuider = new StringBuilder();
		messageBuider.append(message + NEW_LINE);
		process(messageBuider.toString(), channel, webhookURL);
	}

	private static void process(String message, String channel, String webhookURL) {

		Payload payload = Payload.builder()
				.attachments(Collections.singletonList(Attachment.builder().channelName(channel).build())).text(message)
				.build();
		try {
			WebhookResponse webhookResponse = Slack.getInstance().send(webhookURL, payload);
			if (webhookResponse.getCode() == 200)
				System.out.println("Success send slack !");

			System.out.println(webhookResponse.getMessage());
		} catch (IOException e) {
			System.out.println("Unexpected Error! WebHook:" + webhookURL);

		}
	}

}
