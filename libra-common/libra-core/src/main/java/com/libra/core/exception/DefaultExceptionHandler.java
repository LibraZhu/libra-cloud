package com.libra.core.exception;

import com.libra.core.reqres.response.ErrorResponseData;
import com.libra.core.reqres.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.libra.core.constants.AopSortConstants.DEFAULT_EXCEPTION_HANDLER_SORT;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 */
@ControllerAdvice
@Order(DEFAULT_EXCEPTION_HANDLER_SORT)
public class DefaultExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截各个服务的具体异常
     */
    @ExceptionHandler(ApiServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData apiService(ApiServiceException e) {
        log.error("服务具体异常:", e);
        ErrorResponseData errorResponseData = new ErrorResponseData(e.getCode(), e.getErrorMessage());
        errorResponseData.setExceptionClazz(e.getExceptionClassName());
        return errorResponseData;
    }

    /**
     * 拦截请求为空的异常
     */
    @ExceptionHandler(RequestEmptyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData emptyRequest(RequestEmptyException e) {
        return new ErrorResponseData(e.getCode(), e.getErrorMessage());
    }

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseData notFount(ServiceException e) {
        log.info("业务异常:", e);
        return new ErrorResponseData(e.getCode(), e.getErrorMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData serverError(Exception e) {
        log.error("运行时异常:", e);
        return new ErrorResponseData(CoreExceptionEnum.SERVICE_ERROR.getCode(), CoreExceptionEnum.SERVICE_ERROR.getMessage());
    }
}
