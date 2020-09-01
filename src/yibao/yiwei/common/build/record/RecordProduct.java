package yibao.yiwei.common.build.record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunshy
 * get record result
 */
public class RecordProduct {
    private int recordCount;//count
    private List recordList;//results

    protected RecordProduct(){

    }

    public void setRecordCount(int recordCounts) {
        this.recordCount = recordCounts;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public List getRecordList() {
        return recordList;
    }

    public Map<String,Object> result(){
        return result(recordCount, recordList);
    }

    private static Map<String, Object> result(int recordCount, List recordList) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("total",recordCount);
        resultMap.put("rows", recordList);
        return resultMap;
    }
}