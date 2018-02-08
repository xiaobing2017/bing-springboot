package com.bing.boot.server.request;

import com.bing.boot.common.annotation.BlackPhone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Description:参数校验
 * Author: zhangfusheng
 * Date: 2018/2/8 上午10:25
 */
@ApiModel(value = "参数校验对象")
public class ValidateRequest implements Serializable {

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @BlackPhone(list = "13000000001,13000000002")
    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ValidateRequest{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
