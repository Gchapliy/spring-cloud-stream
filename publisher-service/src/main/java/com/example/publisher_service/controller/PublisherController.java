package com.example.publisher_service.controller;

import com.example.publisher_service.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
public class PublisherController {

    @Autowired
    private Source source;

    @GetMapping(value = "/send")
    public void sendMessage() {
        Message message = new Message("Send message from publisher");
        source.output().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping(value = "/send/spam")
    public Message sendSpamMessage() {
        Message message = new Message("I'm sorry, but fuck you");
        source.output().send(MessageBuilder.withPayload(message).build());
        return message;
    }

}
