package com.jovakinn.notificationservice.controller;

import com.jovakinn.notificationservice.kafka.listners.creator.KafkaListenerCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaListenerCreator kafkaListenerCreator;

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestParam String topic) {
        kafkaListenerCreator.createAndRegisterListener(topic);
    }

    @PostMapping(path = "/send")
    @ResponseStatus(HttpStatus.OK)
    public void send(@RequestParam String topic, @RequestParam String message) {
        kafkaTemplate.send(topic, message);
    }
}
