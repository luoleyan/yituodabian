package top.fans39.studentperformance.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.fans39.studentperformance.common.Result;
import javax.servlet.http.HttpServletRequest;

// 全局异常处理器
@ControllerAdvice(basePackages = "top.fans39.studentperformance.controller")
public class GlobalExceptionHandler {
    private static final Log log = LogFactory.get();

    // 异常处理器
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, Exception e){
        log.error("异常信息", e);
        return Result.error();
    }

    // 
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest request, CustomException e){
        return Result.error(e.getMsg());
    }

}
