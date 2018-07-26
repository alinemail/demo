package com.example.demo.controller;

import com.example.demo.model.DemoModel;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Administrator on 2017/8/16.
 */
@RestController
@RequestMapping(value = "/")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/convert/{from}/{to}")
    DemoModel getResult(@PathVariable String from, @PathVariable String to, @RequestParam(value = "rate", defaultValue = "0.0") Double rate, @RequestParam(value = "amount") Double amount) {
        if (isBlank(from) || isBlank(to) || amount == null || amount <= 0) return null;
        if (rate <= 0) rate = null;

        DemoModel model = new DemoModel();
        model.setFrom(from.trim().toUpperCase());
        model.setTo(to.trim().toUpperCase());
        model.setAmount(amount);
        model.setRate(rate);
        return demoService.findModel(model);
    }


    /**
     * 判断是否为null 或者 空
     *
     * @param str 参数
     * @return 是否
     */
    private boolean isBlank(String str) {
        if (str == null || StringUtils.isEmpty(str)) return true;
        else return false;
    }

}