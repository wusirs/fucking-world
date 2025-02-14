package com.world.fucking.advice;


import com.world.fucking.common.Result;
import com.world.fucking.enums.ResultEnum;
import com.world.fucking.exception.BusinessException;
import com.world.fucking.exception.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * <a href='https://blog.csdn.net/weixin_43811057/article/details/127638674'>参考<a/>
 *
 * @author Heisenberg
 * @version 1.0

 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {


    /**
     * 捕获 自定 异常
     */
    @ExceptionHandler({BusinessException.class})
    public Result<Object> handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ex.getStatus(),ex.getMessage());
    }

    /**
     * 参数缺失异常
     * 说明：参数为必填时，若入参中无此参数则会报MissingServletRequestParameterException
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Result<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ResultEnum.VALIDATE_FAILED.getCode(), Objects.requireNonNull(ex.getMessage()));
    }

    /**
     * 参数值校验异常
     * {@code @PathVariable} 和 {@code @RequestParam} 参数值校验不通过时抛出的异常处理
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ResultEnum.VALIDATE_FAILED.getCode(), ex.getMessage());
    }

    /**
     * 参数值类型异常
     * 说明: 定义Integer类型，输入的为String，会出现 MethodArgumentTypeMismatchException异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage(), ex);
        String message = "参数:" + ex.getName() + " 类型错误";
        return Result.failed(ResultEnum.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * {@code @RequestBody} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f == null ? "null" : f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return Result.failed(ResultEnum.VALIDATE_FAILED.getCode(), msg);
    }

    @ExceptionHandler(BindException.class)
    public Result<Object> handleBindException(BindException ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(HttpStatus.BAD_REQUEST.value(),
                ex.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.joining("; "))
        );
    }

    /**
     * 捕获 {@code ForbiddenException} 异常
     */
    @ExceptionHandler({ForbiddenException.class})
    public Result<Object> handleForbiddenException(ForbiddenException ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ResultEnum.FORBIDDEN);
    }


    /**
     * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
     */
    @ExceptionHandler({Exception.class})
    public Result<Object> handle(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ResultEnum.COMMON_FAILED);
    }

    /**
     * 处理已知的系统异常
     */
    @ExceptionHandler({ServletException.class})
    public Result<Object> handle1(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.failed(ex.getMessage());
    }

}
