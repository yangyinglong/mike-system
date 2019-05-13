package cn.hdu.fragmentTax.model.response;

public class OrderResp {
    private Integer id;
    private String phone;
    private String mikeType;
    private Integer number;
    private String  sendDate;
    private String address;
    private String note;
    private String  state; // 1-未付款，2-已付款，3-配送中，4-已送达，0-已删除
    private String price;

    public OrderResp() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMikeType() {
        return mikeType;
    }

    public void setMikeType(String mikeType) {
        this.mikeType = mikeType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
