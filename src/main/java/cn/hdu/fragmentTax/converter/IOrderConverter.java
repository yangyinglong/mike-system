package cn.hdu.fragmentTax.converter;

import cn.hdu.fragmentTax.dao.entity.OrderEntity;
import cn.hdu.fragmentTax.dao.entity.PaymentEntity;
import cn.hdu.fragmentTax.model.request.OrderRequ;
import cn.hdu.fragmentTax.model.response.CountResp;
import cn.hdu.fragmentTax.model.response.OrderResp;
import cn.hdu.fragmentTax.model.response.PaymentResp;

import java.util.List;

public interface IOrderConverter {
    OrderEntity createOrderEntity(Integer id, OrderRequ orderRequ, String date);

    OrderResp createOrderResp(OrderEntity orderEntity);

    PaymentResp createPaymentResp(PaymentEntity paymentEntity);

    CountResp createCountResp(String date, List<OrderEntity> orderEntities);
}
