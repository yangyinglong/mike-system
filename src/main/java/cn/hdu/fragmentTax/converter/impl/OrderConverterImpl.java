package cn.hdu.fragmentTax.converter.impl;

import cn.hdu.fragmentTax.converter.IOrderConverter;
import cn.hdu.fragmentTax.dao.entity.OrderEntity;
import cn.hdu.fragmentTax.dao.entity.PaymentEntity;
import cn.hdu.fragmentTax.model.request.OrderRequ;
import cn.hdu.fragmentTax.model.response.CountResp;
import cn.hdu.fragmentTax.model.response.OrderResp;
import cn.hdu.fragmentTax.model.response.PaymentResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class OrderConverterImpl implements IOrderConverter {
    @Override
    public OrderEntity createOrderEntity(Integer id, OrderRequ orderRequ, String date) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(id);
        BeanUtils.copyProperties(orderRequ, orderEntity);
        orderEntity.setSendDate(date);
        orderEntity.setState(1);
        if (orderRequ.getMikeType().indexOf("500ml") != -1) {
            orderEntity.setPrice(15f*orderEntity.getNumber());
        } else {
            orderEntity.setPrice(8f*orderEntity.getNumber());
        }
        return orderEntity;
    }

    @Override
    public OrderResp createOrderResp(OrderEntity orderEntity) {
        OrderResp orderResp = new OrderResp();
        BeanUtils.copyProperties(orderEntity, orderResp);
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String amou = fnum.format(orderEntity.getPrice());
        orderResp.setPrice(amou);
        if (orderEntity.getState() == 1) {
            orderResp.setState("未支付");
        } else if (orderEntity.getState() == 2) {
            orderResp.setState("已付款");
        } else if (orderEntity.getState() == 3) {
            orderResp.setState("配送中");
        } else if (orderEntity.getState() == 5) {
            orderResp.setState("审核中");
        } else {
            orderResp.setState("已送达");
        }
        return orderResp;
    }

    @Override
    public PaymentResp createPaymentResp(PaymentEntity paymentEntity) {
        PaymentResp paymentResp = new PaymentResp();
        BeanUtils.copyProperties(paymentEntity, paymentResp);
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String amou = fnum.format(paymentEntity.getAmount());
        paymentResp.setAmount(amou);
        if (paymentEntity.getState() == 1) {
            paymentResp.setState("待审核");
        } else if (paymentEntity.getState() == 2) {
            paymentResp.setState("已通过");
        } else {
            paymentResp.setState("错误状态");
        }
        return paymentResp;
    }

    @Override
    public CountResp createCountResp(String date, List<OrderEntity> orderEntities) {
        CountResp countResp = new CountResp();
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        for (OrderEntity orderEntity : orderEntities) {
            if (orderEntity.getMikeType().equals("500ml鲜奶")){
                count1 = count1 + orderEntity.getNumber();
            } else if (orderEntity.getMikeType().equals("500ml酸奶")){
                count2 = count2 + orderEntity.getNumber();
            } else if (orderEntity.getMikeType().equals("200ml鲜奶")){
                count3 = count3 + orderEntity.getNumber();
            } else if (orderEntity.getMikeType().equals("200ml酸奶")){
                count4 = count4 + orderEntity.getNumber();
            }
        }
        countResp.setDate(date);
        countResp.setCount1(count1);
        countResp.setCount2(count2);
        countResp.setCount3(count3);
        countResp.setCount4(count4);
        return countResp;
    }
}
