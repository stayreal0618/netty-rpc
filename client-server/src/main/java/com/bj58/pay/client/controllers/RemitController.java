package com.bj58.pay.client.controllers;

import com.bj58.pay.model.RemitEntity;
import com.bj58.pay.service.RemitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/12/5 下午4:20
 */
@RestController
@RequestMapping("/")
public class RemitController {

    @Autowired
    private RemitService remitService;

    @GetMapping("/remit")
    public String remit() throws Exception {
        return remitService.remit(new RemitEntity());
    }


}
