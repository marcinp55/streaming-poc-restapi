package com.marcin.pawlicki.streaming.poc.restapi.controller;

import com.google.gson.Gson;
import com.streaming.poc.events.MobileClick;
import com.marcin.pawlicki.streaming.poc.restapi.producer.MobileClickProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> postMobileClick(@RequestBody MobileClick mobileClick) {
        try {
            mobileClickProducer.sendMessage(mobileClick);
            return ResponseEntity.ok(gson.toJson(mobileClick));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
