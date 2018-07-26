package com.example.demo.service.Impl;


import com.example.demo.model.DemoModel;
import com.example.demo.service.DemoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service(value = "demoService")
public class DemoServiceImpl implements DemoService {

    @Override
    public DemoModel findModel(DemoModel demoModel) {
        return getModel(demoModel);
    }


    private DemoModel getModel(DemoModel model) {
        if (model.getRate() != null) {

            model.setAmount(model.getAmount() * model.getRate());
            return model;

        } else {

            String to = model.getTo();
            String from = model.getFrom();


            // 相同币种转换
            if (to.equals(from)) {
                model.setRate(1.0);
                return model;
            } else {

                // 人民币转其他
                if (from.equals("CNY")) {

                    Double rate = getRate(to);
                    if (rate == null) return null;
                    model.setRate(rate);
                    model.setAmount(model.getAmount() * model.getRate());

                    //其他转人民币
                } else if (to.equals("CNY")) {

                    Double rate = getRate(from);
                    if (rate == null) return null;
                    model.setRate(1 / rate);
                    model.setAmount(model.getAmount() * model.getRate());

                    // 其他转其他
                } else {

                    Double CNYToRate = getRate(to);  // 人民币转其他汇率
                    if (CNYToRate == null) return null;
                    Double CNYToAmount = model.getAmount() / CNYToRate;  // 人民币值

                    Double CNYFromRate = getRate(from); // 人民币转其他汇率
                    if (CNYFromRate == null) return null;

                    Double CNYFromAmount = model.getAmount() / CNYFromRate;  // 人民币值

                    Double lastRate = CNYFromAmount / CNYToAmount;  // 算出其对其他汇率

                    model.setRate(lastRate);
                    model.setAmount(model.getAmount() * model.getRate());

                }

                return model;
            }


        }


    }


    /**
     * 获取热门汇率集合
     *
     * @return 汇率集合
     */
    private List<DemoModel> getModelList() {
        List<DemoModel> modelList = new ArrayList<DemoModel>(8);
        //人民币对美元
        modelList.add(setModel("USD", 0.1477));
        //人民币对日元
        modelList.add(setModel("JPY", 16.421));
        //人民币对欧元
        modelList.add(setModel("EUR", 0.1263));
        //人民币对英镑
        modelList.add(setModel("GBP", 0.1123));
        //人民币对韩元
        modelList.add(setModel("KRW", 165.9719));
        //人民币对港币
        modelList.add(setModel("HKD", 1.1589));
        //人民币对澳元
        modelList.add(setModel("AUD", 0.1992));
        //人民币对加元
        modelList.add(setModel("CAD", 0.1939));
        return modelList;
    }

    /**
     * 创建汇率数据
     *
     * @param to   币种
     * @param rate 汇率
     * @return 对象
     */
    private DemoModel setModel(String to, Double rate) {
        DemoModel model = new DemoModel();
        model.setFrom("CNY");
        model.setTo(to);
        model.setRate(rate);
        return model;
    }

    /**
     * @param param 参数
     * @return 汇率
     */
    private Double getRate(String param) {
        Double rate = null;
        List<DemoModel> modelList = getModelList();
        for (DemoModel dm : modelList) {
            if (dm.getTo().equals(param)) {
                rate = dm.getRate();
                break;
            }
        }
        return rate;
    }


}
