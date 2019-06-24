package com.hansalser.cloudservice.Common.handler;

import com.fasterxml.classmate.members.ResolvedParameterizedMember;
import com.hansalser.cloudservice.Common.constant.Code;
import com.hansalser.cloudservice.Common.constant.ResponseMessage;
import com.hansalser.cloudservice.Common.exception.CloudServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * @author: chenglin.luo
 * @create: 2019-06-24 17:02
 **/
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseMessage handleException(Exception e) {
        logger.error("系统内部异常，异常信息", e);
        return new ResponseMessage().addCode(Code.SERVER_BUSY);
    }

    @ExceptionHandler(value = CloudServiceException.class)
    public ResponseMessage handleParamsInvalidException(CloudServiceException e) {
        logger.error("系统错误", e);
        return new ResponseMessage().addCode(Code.SERVER_BUSY);
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return FebsResponse
     */
    @ExceptionHandler(BindException.class)
    public ResponseMessage validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        //return new FebsResponse().code(HttpStatus.BAD_REQUEST).message(message.toString());
        return ResponseMessage.success();
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseMessage handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        //return new FebsResponse().code(HttpStatus.BAD_REQUEST).message(message.toString());
        return ResponseMessage.success();
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseMessage handleUnauthorizedException(UnauthorizedException e) {
        logger.error("UnauthorizedException", e);
        //return new FebsResponse().code(HttpStatus.FORBIDDEN).message(e.getMessage());
        return ResponseMessage.success();
    }

    @ExceptionHandler(value = ExpiredSessionException.class)
    public ResponseMessage handleExpiredSessionException(ExpiredSessionException e) {
        logger.error("ExpiredSessionException", e);
        //return new FebsResponse().code(HttpStatus.UNAUTHORIZED).message(e.getMessage());
        return ResponseMessage.success();
    }
}
