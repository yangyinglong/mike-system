package cn.hdu.fragmentTax.service.impl;

import cn.hdu.fragmentTax.converter.IOrderConverter;
import cn.hdu.fragmentTax.dao.entity.OrderEntity;
import cn.hdu.fragmentTax.dao.entity.PaymentEntity;
import cn.hdu.fragmentTax.dao.entity.UsersEntity;
import cn.hdu.fragmentTax.dao.mapper.IOrderMapper;
import cn.hdu.fragmentTax.dao.mapper.IPaymentMapper;
import cn.hdu.fragmentTax.dao.mapper.IUsersMapper;
import cn.hdu.fragmentTax.model.request.NoticePayRequ;
import cn.hdu.fragmentTax.model.request.OrderRequ;
import cn.hdu.fragmentTax.model.request.QueryRequ;
import cn.hdu.fragmentTax.model.response.CountResp;
import cn.hdu.fragmentTax.model.response.OrderResp;
import cn.hdu.fragmentTax.model.response.PaymentResp;
import cn.hdu.fragmentTax.model.response.QueryMoneyResp;
import cn.hdu.fragmentTax.service.IOrderService;
import cn.hdu.fragmentTax.utils.DateUtil;
import cn.hdu.fragmentTax.utils.FormatUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IUsersMapper usersMapper;

    @Autowired
    private IOrderMapper orderMapper;

    @Autowired
    private IPaymentMapper paymentMapper;

    @Autowired
    private IOrderConverter orderConverter;

    @Override
    public Map<String, Object> orderReserve(OrderRequ orderRequ) {
        Map<String, Object> resp = new HashMap<>();
        try {
            UsersEntity usersEntity = usersMapper.queryByPhone(orderRequ.getPhone());
            orderRequ.setStartDate(DateUtil.getChinaDateTime(orderRequ.getStartDate()));
            orderRequ.setEndDate(DateUtil.getChinaDateTime(orderRequ.getEndDate()));
            List<String> result = DateUtil.getBetweenDatesForString(DateUtil.getDateFromString(orderRequ.getStartDate()),
                    DateUtil.getDateFromString(orderRequ.getEndDate()), orderRequ.getInterval());
            Float allPrice = 0f;
            for (String date : result) {
                OrderEntity orderEntity = orderConverter.createOrderEntity(usersEntity.getId(), orderRequ, date);
                orderMapper.insertOrder(orderEntity);
                allPrice = allPrice + orderEntity.getPrice();
            }
            resp.put("c", 200);
            resp.put("r", allPrice);
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }
        return resp;
    }

    @Override
    public Map<String, Object> queryMoney(String phone) {
        Map<String, Object> resp = new HashMap<>();
        List<Integer> orderIds = new ArrayList<Integer>();
        try {
             List<OrderEntity> orderEntities = orderMapper.queryNoPayByPhone(phone);
            float allPrice = 0f;
            for (OrderEntity orderEntity : orderEntities) {
                allPrice = allPrice + orderEntity.getPrice();
                orderIds.add(orderEntity.getId());
            }
            QueryMoneyResp queryMoneyResp = new QueryMoneyResp();
            DecimalFormat fnum = new DecimalFormat("##0.00");
            String amou = fnum.format(allPrice);
            queryMoneyResp.setMoney(amou);
            queryMoneyResp.setOrderIds(orderIds);
            queryMoneyResp.setPhone(phone);
            resp.put("c", 200);
            resp.put("r", queryMoneyResp);
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }
        return resp;
    }

    @Override
    public Map<String, Object> noticePayment(NoticePayRequ noticePayRequ) {
        Map<String, Object> resp = new HashMap<>();
        PaymentEntity paymentEntity = new PaymentEntity();
        try {
            UsersEntity usersEntity = usersMapper.queryByPhone(noticePayRequ.getPhone());
            paymentEntity.setUserId(usersEntity.getId());
            paymentEntity.setAmount(noticePayRequ.getAmount());
            paymentEntity.setState(1);
            paymentEntity.setPhone(noticePayRequ.getPhone());
            paymentEntity.setCreatedTime(DateUtil.getCurrentDatetime());
            String orderIds = new String();
            for (Integer integer : noticePayRequ.getOrderIds()) {
                orderIds = orderIds + integer.toString() + ',';
            }
            orderIds = orderIds.substring(0, orderIds.length() - 1);
            paymentEntity.setOrderIds(orderIds);
            paymentMapper.insertPayment(paymentEntity);
            orderMapper.updateStateById(orderIds, 5);
            resp.put("c", 200);
            resp.put("r", "通知成功");
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }

        return resp;
    }

    @Override
    public Map<String, Object> showOrders(String phone) {
        Map<String, Object> resp = new HashMap<>();
        List<OrderResp> orderResps = new ArrayList<OrderResp>();
        try {
            List<OrderEntity> orderEntities = orderMapper.queryAllByPhone(phone);
            for (OrderEntity orderEntity : orderEntities) {
                OrderResp orderResp = orderConverter.createOrderResp(orderEntity);
                orderResps.add(orderResp);
            }
            resp.put("c", 200);
            resp.put("r", orderResps);
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }
        return resp;
    }

    @Override
    public Map<String, Object> deleOrder(Integer id) {
        Map<String, Object> resp = new HashMap<>();
        try {
            orderMapper.deleteById(id);
            resp.put("c", 200);
            resp.put("r", "删除成功");
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }
        return resp;
    }

    @Override
    public Map<String, Object> queryOrdersForAdmin(QueryRequ queryRequ) {
        Map<String, Object> resp = new HashMap<>();
        List<OrderEntity> orderEntities = null;
        List<OrderResp> orderResps = new ArrayList<OrderResp>();
        if (!"heluMikeAdmin".equals(queryRequ.getToken())) {
            resp.put("c", 310);
            resp.put("r", "请求错误");
            return resp;
        }
        try {
            if (!FormatUtil.isEmpty(queryRequ.getStartDate()) && !FormatUtil.isEmpty(queryRequ.getEndDate())) {
                queryRequ.setStartDate(DateUtil.getChinaDateTime(queryRequ.getStartDate()));
                queryRequ.setEndDate(DateUtil.getChinaDateTime(queryRequ.getEndDate()));
                Date date1 = DateUtil.getDateFromString(queryRequ.getStartDate());
                Date date2 = DateUtil.getDateFromString(queryRequ.getEndDate());
                List<String> dateList = DateUtil.getBetweenDatesForString(date1, date2, 1);
                String dates = FormatUtil.strings2String(dateListToArr(dateList));
                String states = FormatUtil.strings2String(getIntAuditStatus(queryRequ.getStatus()));
                orderEntities = orderMapper.queryForAdmin1(dates, states);
            } else {
                String states = FormatUtil.strings2String(getIntAuditStatus(queryRequ.getStatus()));
                String date = DateUtil.getCurrentDatetime().split(" ")[0];
                orderEntities = orderMapper.queryForAdmin2(date, states);
            }
        } catch (Exception e) {
            resp.put("c", 310);
            resp.put("r", "请求错误");
            return resp;
        }
        for (OrderEntity orderEntity : orderEntities) {
            OrderResp orderResp = orderConverter.createOrderResp(orderEntity);
            orderResps.add(orderResp);
        }
        resp.put("c", 200);
        resp.put("r", orderResps);
        return resp;
    }

    @Override
    public Map<String, Object> showPaymentForAdmin(QueryRequ queryRequ) {
        Map<String, Object> resp = new HashMap<>();
        if (!"heluMikeAdmin".equals(queryRequ.getToken())) {
            resp.put("c", 310);
            resp.put("r", "请求错误");
            return resp;
        }
        List<PaymentResp> paymentResps = new ArrayList<PaymentResp>();
        String states = FormatUtil.strings2String(getIntAuditStatus2(queryRequ.getStatus()));
        List<PaymentEntity> paymentEntities = paymentMapper.queryForAdmin(states);
        for (PaymentEntity paymentEntity : paymentEntities) {
            PaymentResp paymentResp = orderConverter.createPaymentResp(paymentEntity);
            paymentResps.add(paymentResp);
        }
        resp.put("c", 200);
        resp.put("r", paymentResps);

        return resp;
    }

    @Override
    public Map<String, Object> confirmPayment(Integer id) {
        Map<String, Object> resp = new HashMap<>();
        try {
            PaymentEntity paymentEntity = paymentMapper.queryByKey(id);
            orderMapper.updateStateById(paymentEntity.getOrderIds(), 2);
            paymentMapper.updateStateById(id, 2);
            resp.put("c", 200);
            resp.put("r", "成功");
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }
        return resp;
    }

    @Override
    public Map<String, Object> confirmSended(Integer id) {
        Map<String, Object> resp = new HashMap<>();
        try {
            orderMapper.updateState(id, 4);
            resp.put("c", 200);
            resp.put("r", "成功");
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }
        return resp;
    }

    @Override
    public Map<String, Object> confirmSending(Integer id) {
        Map<String, Object> resp = new HashMap<>();
        try {
            orderMapper.updateState(id, 3);
            resp.put("c", 200);
            resp.put("r", "成功");
        } catch (Exception e) {
            resp.put("c", 401);
            resp.put("r", "数据库异常，请联系管理员");
        }
        return resp;
    }

    @Override
    public Map<String, Object> showCountForAdmin(QueryRequ queryRequ) {
        Map<String, Object> resp = new HashMap<>();
        List<OrderEntity> orderEntities = null;
        List<CountResp> countResps = new ArrayList<CountResp>();
        if (!"heluMikeAdmin".equals(queryRequ.getToken())) {
            resp.put("c", 310);
            resp.put("r", "请求错误");
            return resp;
        }
        try {
            if (!FormatUtil.isEmpty(queryRequ.getStartDate()) && !FormatUtil.isEmpty(queryRequ.getEndDate())) {
                queryRequ.setStartDate(DateUtil.getChinaDateTime(queryRequ.getStartDate()));
                queryRequ.setEndDate(DateUtil.getChinaDateTime(queryRequ.getEndDate()));
                Date date1 = DateUtil.getDateFromString(queryRequ.getStartDate());
                Date date2 = DateUtil.getDateFromString(queryRequ.getEndDate());
                List<String> dateList = DateUtil.getBetweenDatesForString(date1, date2, 1);
//                String dates = FormatUtil.strings2String(dateListToArr(dateList));
                String states = FormatUtil.strings2String(getIntAuditStatus(queryRequ.getStatus()));
                for (String date : dateList) {
                    orderEntities = orderMapper.queryForAdmin2(date, states);
                    CountResp countResp = orderConverter.createCountResp(date, orderEntities);
                    countResps.add(countResp);
                }
            } else {
                String states = FormatUtil.strings2String(getIntAuditStatus(queryRequ.getStatus()));
                String date = DateUtil.getCurrentDatetime().split(" ")[0];
                orderEntities = orderMapper.queryForAdmin2(date, states);
                CountResp countResp = orderConverter.createCountResp(date, orderEntities);
                countResps.add(countResp);
            }
        } catch (Exception e) {
            resp.put("c", 310);
            resp.put("r", "请求错误");
            return resp;
        }
        resp.put("c", 200);
        resp.put("r", countResps);
        return resp;
    }


    private String[] dateListToArr(List<String> dates) {
        String dateArr[] = new String[dates.size()];
        int i = 0;
        for (String s : dates) {
            dateArr[i] = "'" + s + "'";
            i = i + 1;
        }
        return dateArr;
    }

    private String[] getIntAuditStatus(String[] auditStatus) {
//        1-未付款，2-已付款，3-配送中，4-已送达，0-已删除，5-待审核
        for (int i = 0; i < auditStatus.length; i++) {
            if (auditStatus[i].equals("已删除")) {
                auditStatus[i] = "0";
            } else if (auditStatus[i].equals("未付款")) {
                auditStatus[i] = "1";
            } else if (auditStatus[i].equals("已付款")) {
                auditStatus[i] = "2,3,4,5";
            } else if (auditStatus[i].equals("配送中")) {
                auditStatus[i] = "3";
            } else if (auditStatus[i].equals("已送达")) {
                auditStatus[i] = "4";
            } else if (auditStatus[i].equals("待审核")) {
                auditStatus[i] = "5";
            }
        }
        return auditStatus;
    }

    private String[] getIntAuditStatus2(String[] auditStatus) {
//        1-未付款，2-已付款，3-配送中，4-已送达，0-已删除，5-待审核
        for (int i = 0; i < auditStatus.length; i++) {
            if (auditStatus[i].equals("已删除")) {
                auditStatus[i] = "0";
            } else if (auditStatus[i].equals("待审核")) {
                auditStatus[i] = "1";
            } else if (auditStatus[i].equals("已通过")) {
                auditStatus[i] = "2";
            }
        }
        return auditStatus;
    }
}
