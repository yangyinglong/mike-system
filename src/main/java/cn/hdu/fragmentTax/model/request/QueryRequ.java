package cn.hdu.fragmentTax.model.request;

public class QueryRequ {
    private String phone;
    private String[] status;
    private String token;
    private String startDate;
    private String endDate;

    public QueryRequ() {
    }

    public QueryRequ(String phone, String[] status, String token, String startDate, String endDate) {
        this.phone = phone;
        this.status = status;
        this.token = token;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
