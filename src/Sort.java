
public class Sort {
    private String field;//字段名
    private String direct;//排序方向（ASC/DESC），前端请写成常量

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }
}
