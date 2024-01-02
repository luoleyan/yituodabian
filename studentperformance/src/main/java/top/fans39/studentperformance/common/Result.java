package top.fans39.studentperformance.common;

// Result对象用于封装请求结果
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Result {
    private static  final  String SUCCESS_CODE = "200";
    private static  final  String ERROR_CODE = "500";
    private String code;
    private String msg;
    private Object data;

    // 构造方法私有化

    private Result(Object data) {
        this.data = data;
    }

    public Result() {
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMsg("请求成功");
        return result;
    }

    public static Result success(Object data) {
        Result result = success();
        result.setData(data);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg("请求失败");
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }

}
