package com.tiger.tigeredu.servicebase.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA
 *
 * @author TIGER
 * @Package: com.tiger.tigeredu.servicebase.exception
 * @ClassName: TigerException
 * @date 2020/6/30 20:38
 * @Description:
 */
@Data
@AllArgsConstructor  //生成有参数的构造方法
@NoArgsConstructor  //生成无参数的构造方法
public class TigerException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "异常信息")
    private String msg;
}
