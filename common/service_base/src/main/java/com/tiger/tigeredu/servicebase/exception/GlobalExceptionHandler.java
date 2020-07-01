package com.tiger.tigeredu.servicebase.exception;

import com.tiger.tigeredu.commonutils.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by IntelliJ IDEA
 *
 * @author TIGER
 * @Package: com.tiger.tigeredu.servicebase.exception
 * @ClassName: GlobalExceptionHandler
 * @date 2020/6/30 18:37
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 操作数据库异常
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public RespBean sqlException(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();
        return RespBean.error().message("数据重复或有关联数据，操作失败！");
    }

    /**
     * 自定义异常
     * @param ex
     * @return
     */
    @ExceptionHandler(TigerException.class)
    public RespBean tigerExpected(TigerException ex) {
        log.error(ex.getMsg());
        ex.printStackTrace();
        return RespBean.error().code(ex.getCode()).message(ex.getMsg());
    }
}
