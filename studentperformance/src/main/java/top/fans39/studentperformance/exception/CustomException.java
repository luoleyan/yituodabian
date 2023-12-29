package top.fans39.studentperformance.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomException extends RuntimeException {
    private String msg;
    public CustomException(String msg){
        this.msg = msg;
    }
}
