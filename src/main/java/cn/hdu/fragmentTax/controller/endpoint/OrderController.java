package cn.hdu.fragmentTax.controller.endpoint;


import cn.hdu.fragmentTax.model.request.NoticePayRequ;
import cn.hdu.fragmentTax.model.request.OrderRequ;
import cn.hdu.fragmentTax.model.request.QueryRequ;
import cn.hdu.fragmentTax.model.request.RegisterRequ;
import cn.hdu.fragmentTax.model.response.OrderResp;
import cn.hdu.fragmentTax.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Component
@Path("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Path("/reserve")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> reserve(OrderRequ orderRequ) {
        Map<String, Object> resp = null;
        resp = orderService.orderReserve(orderRequ);
        return resp;
    }

    @Path("/querymoney")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> queryMoney(String phone) {
        Map<String, Object> resp = null;
        resp = orderService.queryMoney(phone);
        return resp;
    }

    @Path("/noticepayment")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> noticePayment(NoticePayRequ noticePayRequ) {
        Map<String, Object> resp = null;
        resp = orderService.noticePayment(noticePayRequ);
        return resp;
    }

    @Path("/showorder")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> showOrder(String phone) {
        Map<String, Object> resp = null;
        resp = orderService.showOrders(phone);
        return resp;
    }

    @Path("/deleorder")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> deleOrder(Integer id) {
        Map<String, Object> resp = null;
        resp = orderService.deleOrder(id);
        return resp;
    }

    @Path("/showorderforadmin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> showOrderForAdmin(QueryRequ queryRequ) {
        Map<String, Object> resp = null;
        resp = orderService.queryOrdersForAdmin(queryRequ);
        return resp;
    }


    @Path("/showpaymentforadmin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> showPaymentForAdmin(QueryRequ queryRequ) {
        Map<String, Object> resp = null;
        resp = orderService.showPaymentForAdmin(queryRequ);
        return resp;
    }

    @Path("/confirmpayment")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> confirmPayment(Integer id) {
        Map<String, Object> resp = null;
        resp = orderService.confirmPayment(id);
        return resp;
    }

    @Path("/confirmsended")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> confirmSended(Integer id) {
        Map<String, Object> resp = null;
        resp = orderService.confirmSended(id);
        return resp;
    }

    @Path("/confirmsending")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> confirmSending(Integer id) {
        Map<String, Object> resp = null;
        resp = orderService.confirmSending(id);
        return resp;
    }

    @Path("/showcountforadmin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> showCountForAdmin(QueryRequ queryRequ) {
        Map<String, Object> resp = null;
        resp = orderService.showCountForAdmin(queryRequ);
        return resp;
    }
}
