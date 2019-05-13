package cn.hdu.fragmentTax.model.request;

import java.util.List;

public class NoticePayRequ {
    private String phone;
    private float amount;
    private List<Integer> orderIds;

    public NoticePayRequ() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }
}
