package yoziming.pojo;

import java.util.List;

/**
 * Page是分頁的模型物件
 * @param <T> 是具體的模塊的javaBean類
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    // 當前頁碼
    private Integer pageNo;
    // 總頁碼
    private Integer pageTotal;
    // 當前頁顯示數量
    private Integer pageSize = PAGE_SIZE;
    // 總記錄數
    private Integer pageTotalCount;
    // 當前頁數據
    private List<T> items;
    // 分頁條的請求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        /* 數據邊界的有效檢查，比如有人手賤想查第-5頁或9999999頁 */
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageTotal>0){
            if (pageNo > pageTotal) {
                pageNo = pageTotal;
            }
        }


        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
