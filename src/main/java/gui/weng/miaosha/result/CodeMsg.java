package gui.weng.miaosha.result;

public class CodeMsg {
    private int code;
    private String  msg;

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务器异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101,"参数校验异常：%s");
    // 登录模块 5002xx
    public static CodeMsg LOGIN_ERROR = new CodeMsg(500200,"登录异常");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211,"密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212,"手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213,"手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214,"手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215,"密码错误");
    // 商品模块 5003xx
    public static CodeMsg GOOD_ERERROR = new CodeMsg(500300,"商品异常");
    // 订单模块 5004xx
    public static CodeMsg ORDER_ERROR = new CodeMsg(500400,"订单异常");
    // 秒杀模块 5005xx
    public static CodeMsg MS_ERROR = new CodeMsg(500500,"秒杀异常");

    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        this.code = this.code;
         String msg = String.format(this.msg,args);
         return new CodeMsg(code,msg);
    }


    public int getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }
}
