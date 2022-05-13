package com.tistory.shanepark.spring;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

@Controller
@RequestMapping("/redirect")
public class RedirectTestController {

    @GetMapping("body")
    @ResponseBody
    public ResponseEntity<?> redirect() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("prefix")
    public String redirectPrefix() {
        return "redirect:/";
    }

    @GetMapping("error")
    @ResponseBody
    public String error() {
        return "redirect:/";
    }

}
