import java.io.Serializable;

public class Response<T> implements Serializable {
    private Boolean success = true; //标志位，必选
    private String message; //提示信息，可选
    private String errCode; //错误码，可选
    private T data; //返回数据，可选

    public Response(){ }

    public Response(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(Boolean success, String message, String errCode) {
        this.success = success;
        this.message = message;
        this.errCode = errCode;
    }

    public Response(T data) {
        this.data = data;
        this.message = "请求处理成功";
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
