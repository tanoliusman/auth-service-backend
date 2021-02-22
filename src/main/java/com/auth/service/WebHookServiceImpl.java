package com.auth.service;


import com.auth.dto.BookingWebHooksRequest;
import com.auth.repository.WebhookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebHookServiceImpl {

    @Autowired
    private WebhookRepository repository;

    public void saveWebhook(BookingWebHooksRequest request) {
        repository.save(request);
    }

    public List<BookingWebHooksRequest> getWebhooks(){
        return repository.findAll();
    }
}
