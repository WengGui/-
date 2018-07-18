package gui.weng.miaosha.exception;

import gui.weng.miaosha.result.CodeMsg;
import gui.weng.miaosha.result.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class GobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)  // 拦截所有异常
    public Result<String> exceptionHandler(HttpServletRequest request,Exception ex){
        if(ex instanceof BindException){
            BindException bindException = (BindException)ex;
            List<ObjectError> allErrors = bindException.getAllErrors();
            ObjectError error = allErrors.get(0);
            String msg =error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
