import java.util.List;
import java.util.Map;

/**
 * 分页统一格式
 * C 查询条件实体 T 返回列表中的数据实体
 */
public class Pagination<C,T> {
    public static final Integer PAGE_NO = 1; //默认当前页码
    public static final Integer PAGE_SIZE = 20; //默认每一页的记录数

    private Integer pageNo = PAGE_NO; //当前页码
    private Integer pageSize = PAGE_SIZE; //每一页的记录数
    private Integer total = 0; //总记录数
    private Integer pages = 0; //总页数
    private C cond; //查询条件
    private List<Sort> orderBy;//排序参数列表，可选
    private List<T> list; //列表数据

    /**
     * 分页对象初始化
     * @param pageNo
     * @param pageSize
     * @param cond
     */
    public Pagination(Integer pageNo, Integer pageSize, C cond) {
        this.pageNo = pageNo==null||pageNo<0 ? PAGE_NO : pageNo;
        this.pageSize = pageSize==null||pageSize<0 ? PAGE_SIZE: pageSize;
        this.cond = cond;
    }

    /**
     * 查询结果包装
     * @param total
     * @param list
     * @return
     */
    public Pagination page(Integer total, List<T> list) {
        this.total = total;
        this.list = list;
        if(total!=null && total>=0){ // 计算总页数
            this.pages = total%this.pageSize==0 ? total/this.pageSize : total/this.pageSize+1;
        }
        return this;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Sort> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(List<Sort> orderBy) {
        this.orderBy = orderBy;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public C getCond() {
        return cond;
    }

    public void setCond(C cond) {
        this.cond = cond;
    }
}
