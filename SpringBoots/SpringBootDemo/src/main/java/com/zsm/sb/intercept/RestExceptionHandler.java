package com.zsm.sb.intercept;

import com.zsm.sb.model.ResultVO;
import org.apache.ibatis.javassist.NotFoundException;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


/**
 * @Author: zengsm.
 * @Description: TODO()
 * @Date:Created in 2018/9/20.
 * @Modified By:
 */
//@ControllerAdvice(annotations = RestController.class)
@RestControllerAdvice
public class RestExceptionHandler // extends ResponseEntityExceptionHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    //@ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private <T> ResultVO<T> sqlExceptionHandler(HttpServletRequest request, SQLException e)
    {
        LOGGER.error("sqlExceptionHandler", e);
        return ResultVO.fail("SQLException: 参数格式有误", 406);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //@ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private <T> ResultVO<T> methodArgumentNotValidExceptionHandler(HttpServletRequest request,
                                                                   MethodArgumentNotValidException e)
    {
        LOGGER.error("methodArgumentNotValidExceptionHandler", e);
        return ResultVO.fail("methodArgumentNotValidExceptionHandler:服务器发生错误", 500);
    }

    @ExceptionHandler(NotFoundException.class)
    //@ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private <T> ResultVO<T> notFoundExceptionHandler(HttpServletRequest request, NotFoundException e)
    {
        LOGGER.error("notFoundExceptionHandler", e);
        return ResultVO.fail("notFoundExceptionHandler:没有访问资源", 404);
    }

    @ExceptionHandler(SystemException.class)
    //@ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private <T> ResultVO<T> systemExceptionHandler(HttpServletRequest request, SystemException e)
    {
        LOGGER.error("systemExceptionHandler", e);
        return ResultVO.fail("systemExceptionHandler:系统繁忙，请稍后重试", 503);
    }

    @ExceptionHandler(Exception.class)
    //@ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private <T> ResultVO<T> exceptionHandler(HttpServletRequest request, Exception e)
    {
        LOGGER.error("exceptionHandler", e);
        return ResultVO.fail("exceptionHandler:服务不可用", 503);
    }
}
