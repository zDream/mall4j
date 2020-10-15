package com.yami.shop.api.controller;

import com.yami.shop.bean.app.param.IntegralParam;
import com.yami.shop.bean.model.IntegralDetail;
import com.yami.shop.security.util.SecurityUtils;
import com.yami.shop.service.IntegralDetailService;
import com.yami.shop.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author : AnDi
 * @CreateTime : 2020-10-12
 * @Description :
 **/
@RestController
@RequestMapping("/integral")
public class IntegralController {

    @Autowired
    IntegralService integralService;
    @Autowired
    IntegralDetailService integralDetailService;
    @PostMapping
    public ResponseEntity<Void> saveIntegral(@RequestBody  IntegralParam param) {
        String userId = SecurityUtils.getUser().getUserId();

        integralService.integralManage(userId,param);
        return ResponseEntity.ok().build();
    }

}
