package com.auth.controller;


import com.auth.dto.BookingWebHooksRequest;
import com.auth.exceptions.AuthServiceException;
import com.auth.service.WebHookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/***
 * This class is for personal user. please ignore all it's associated values;
 */
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private WebHookServiceImpl webHookService;

    @PostMapping("/webhook")
    public ResponseEntity saveWebhook( @RequestParam("data") String data, @RequestParam("sign") String sign) throws AuthServiceException {
        webHookService.saveWebhook(new BookingWebHooksRequest(data,sign));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/webhook")
    public ResponseEntity getWebhooks() throws AuthServiceException {
        return ResponseEntity.ok(webHookService.getWebhooks());
    }
}
