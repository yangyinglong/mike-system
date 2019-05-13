package cn.hdu.fragmentTax.model.response;

import java.util.List;

public class QueryMoneyResp {
    private String phone;
    private String money;
    private List<Integer> orderIds;

    public QueryMoneyResp() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }
}
