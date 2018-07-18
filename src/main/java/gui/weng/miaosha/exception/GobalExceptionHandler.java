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


/**
 * 全局异常处理器，跟其他的Controller类差不多
 * 不同的是普通的Controller的参数来自于前端网页的传输处理的值，其触发也因为前台的请求触发
 *
 * 该Controller的触发是有后台抛出的异常，参数也是后台抛出的异常信息
 * 使用该类对捕获的异常进行包装后，直接return，返回给前台页面接收(这个和其他Controller一样)
 *
 * 这一功能有什么用？
 * 我不需要每个方法都去为了去考虑异常错误信息而去定义一个特殊的数据结构，用以反馈给前台，
 * 我的各个Controller和Service，只需考虑正常无错误执行后需要返回的结果类型即可，一旦出现异常，
 * 在方法中抛出，交给该类处理，反馈给前台
 *
 */
@ControllerAdvice
@ResponseBody
public class GobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)  // 拦截所有异常
    public Result<String> exceptionHandler(HttpServletRequest request,Exception ex){
        if(ex instanceof GobalException){
            GobalException gobalException = (GobalException)ex;
            CodeMsg msg = gobalException.getCm();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }
        else if(ex instanceof BindException){
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
