package gui.weng.miaosha.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data){
        this.code = 0;
        this.msg = "success";
        this.data=data;
    }

    private Result(CodeMsg codeMsg){
        if (codeMsg == null){
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    /**
     * 成功时调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 失败时调用
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
