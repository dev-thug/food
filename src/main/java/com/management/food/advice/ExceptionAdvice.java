package com.management.food.advice;

import com.management.food.advice.exception.AppError;
import com.management.food.advice.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final MessageSource messageSource;


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected AppError defaultException(HttpServletRequest request, Exception e) {
        // 예외 처리의 메시지를 MessageSource 에서 가져오도록 수정
        return AppError.builder().code(Integer.valueOf(getMessage("unKnown.code"))).message(getMessage("unKnown.message")).build();
    }

    @ExceptionHandler(NotFoundResourceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected AppError userNotFoundException(HttpServletRequest request, NotFoundResourceException e) {
        // 예외 처리의 메시지를 MessageSource 에서 가져오도록 수정
        return AppError.builder().code(Integer.valueOf(getMessage("notFoundResource.code"))).message(getMessage("notFoundResource.message")).build();
    }

    // code 정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // code 정보, 추가 argument 로 현재 locale 에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
