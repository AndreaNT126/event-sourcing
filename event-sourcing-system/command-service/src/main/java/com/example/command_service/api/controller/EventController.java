package com.example.command_service.api.controller;

import com.example.command_service.account.EventStoreExam;
import com.example.command_service.api.request.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    EventStoreExam service;

    @PostMapping("/append")
    public String appendEvent(@RequestBody EventDto request) {
        return service.appendStream(request.getStreamName(), request.getEventType(), request.getData());
    }

    @PostMapping("/appendAsync")
    public void appendEventAsync(@RequestBody EventDto request) {
        service.threadCompletableFuture(request);
    }

    @GetMapping
    public String getWelcome() {
        return "hello";
    }

    @GetMapping("/read")
    public String readEvents(@RequestParam(name = "name",required = true) String streamName) {
        return service.readStream(streamName);
    }
}
