package com.tiger.tigeredu.commonutils;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA
 *
 * @author TIGER
 * @Package: com.tiger.tigeredu.commonutils
 * @ClassName:
 * @date 2020/6/30 0:46
 * @Description: 统一返回结果的类
 */
@Data
public class RespBean {

    @ApiModelProperty(value = "是否成功")
    private boolean success;
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private RespBean() {
    }

    public static RespBean ok() {
        RespBean rb = new RespBean();
        rb.setSuccess(true);
        rb.setCode(ResultCode.SUCCESS);
        rb.setMessage("成功！");
        return rb;
    }

    public static RespBean error() {
        RespBean rb = new RespBean();
        rb.setSuccess(false);
        rb.setCode(ResultCode.ERROR);
        rb.setMessage("失败！");
        return rb;
    }

    public static RespBean build() {
        return new RespBean();
    }

    public RespBean success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public RespBean code(Integer code){
        this.setCode(code);
        return this;
    }

    public RespBean message(String msg){
        this.setMessage(msg);
        return this;
    }

    public RespBean data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public RespBean data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
