package cn.hdu.fragmentTax.service;

import cn.hdu.fragmentTax.model.request.NoticePayRequ;
import cn.hdu.fragmentTax.model.request.OrderRequ;
import cn.hdu.fragmentTax.model.request.QueryRequ;
import cn.hdu.fragmentTax.model.response.OrderResp;

import java.util.Map;

public interface IOrderService {
    Map<String,Object> orderReserve(OrderRequ orderRequ);

    Map<String,Object> queryMoney(String phone);

    Map<String,Object> noticePayment(NoticePayRequ noticePayRequ);

    Map<String,Object> showOrders(String phone);

    Map<String,Object> deleOrder(Integer id);

    Map<String,Object> queryOrdersForAdmin(QueryRequ queryRequ);

    Map<String,Object> showPaymentForAdmin(QueryRequ queryRequ);

    Map<String,Object> confirmPayment(Integer id);

    Map<String,Object> confirmSended(Integer id);

    Map<String,Object> confirmSending(Integer id);

    Map<String,Object> showCountForAdmin(QueryRequ queryRequ);
}
