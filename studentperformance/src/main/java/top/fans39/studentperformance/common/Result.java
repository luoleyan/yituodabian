package top.fans39.studentperformance.common;
import lombok.Data;
import lombok.ToString;

// 封装结果信息
@ToString
@Data
public class Result {
    private static  final  String SUCCESS_CODE = "200";  // 设置成功状态码为200
    private static  final  String ERROR_CODE = "500";  // 设置错误状态码500
    private String code;
    private String msg;
    private Object data;

    // 构造方法私有化

    private Result(Object data) {
        this.data = data;
    }

    public Result() {
    }

    // 在本次操作成功时，设置成功状态码和“请求成功”状态信息
    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMsg("请求成功");
        return result;
    }

    // 操作成功时再把数据封装进data变量中
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
