package com.gg.exception.exceptionHandler;

import com.gg.exception.error.ErrorType;
import com.gg.exception.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobleExceptionHanlder {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error2");
        return mav;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorType<String> myExceptionHandler(HttpServletRequest request, MyException e) throws Exception {
        ErrorType<String> result = new ErrorType<>();
        result.setCode("100");
        result.setUrl(request.getRequestURI());
        result.setMessage("error");
        result.setData("myException error");
        return result;
    }
}
