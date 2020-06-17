package com.springcloud.entities;

import java.io.Serializable;

public class Payment implements Serializable {
    private Integer code;
    private String serial;

    public Payment(Integer code, String serial) {
        this.code = code;
        this.serial = serial;
    }
    public Payment(){
        super();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
