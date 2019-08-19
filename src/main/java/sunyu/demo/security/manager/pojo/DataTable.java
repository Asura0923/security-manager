package sunyu.demo.security.manager.pojo;

import java.util.List;

/**
 * datatables vo
 *
 * @author SunYu
 */
public class DataTable {
    private Long draw;
    private Long start;
    private Long pageNum;
    private Long length;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List datas;

    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        if (recordsFiltered == null) {
            return getRecordsTotal();
        }
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public Long getPageNum() {
        if (start == 0) {
            return 1L;
        }
        return (start + length) / start;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }
}
