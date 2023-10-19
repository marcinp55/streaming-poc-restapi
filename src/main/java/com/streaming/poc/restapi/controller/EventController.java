package com.streaming.poc.restapi.controller;

import com.google.gson.Gson;
import com.streaming.poc.events.MobileClick;
import com.streaming.poc.restapi.producer.MobileClickProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    protected final MobileClickProducer mobileClickProducer;
    private final Gson gson = new Gson();

    @Autowired
    public EventController(MobileClickProducer mobileClickProducer) {
        this.mobileClickProducer = mobileClickProducer;
    }

    @PostMapping(value = "/events/mobileClick", produces = "application/json")
    public String postMobileClick(@RequestBody MobileClick mobileClick) {
        mobileClickProducer.sendMessage(mobileClick);

        return this.gson.toJson(mobileClick);
    }
}
