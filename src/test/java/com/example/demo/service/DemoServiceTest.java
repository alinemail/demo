package com.example.demo.service;

import com.example.demo.model.DemoModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void findModel() {
        DemoModel demoModel = new DemoModel();
        demoModel.setFrom("USD");
        demoModel.setTo("CNY");
        demoModel.setRate(null);
        demoModel.setAmount(10.0);
        demoModel = demoService.findModel(demoModel);

        System.out.println("测试结果:         " + demoModel.toString());

    }
}