package cn.hdu.fragmentTax.model.request;

public class LoginRequ {
    private String phone;
    private String password;
    private Integer state;

    public LoginRequ() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
