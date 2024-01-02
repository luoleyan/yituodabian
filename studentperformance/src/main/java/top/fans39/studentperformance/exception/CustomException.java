package top.fans39.studentperformance.exception;

import lombok.Data;
import lombok.ToString;

// 自定义异常处理器
@Data
@ToString
public class CustomException extends RuntimeException {
    private String msg;
    // 取得运行时错误信息
    public CustomException(String msg){
        this.msg = msg;
    }
}
