package yibao.yiwei.common.build.record;

import yibao.yiwei.common.SessionKey;
import yibao.yiwei.entity.system.CustomerUser;
import yibao.yiwei.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sunshy
 * build record result
 */
public abstract class IRecordBuilder<T> {
    protected RecordProduct recordProduct = new RecordProduct();
    protected String cusId;
    protected String startDate;
    protected String endDate;
    protected String page;
    protected String rows;
    protected String tableName;
    protected String[] newTables;//new table list
    protected int[] totals;
    protected int total;

    public IRecordBuilder(HttpServletRequest request, Date startDate, Date endDate, String page, String rows){
        buildParam(request,startDate,endDate,page,rows);
    }

    private void buildParam(HttpServletRequest request,Date startDate,Date endDate,String page, String rows){
        CustomerUser user  = (CustomerUser)request.getSession().getAttribute(SessionKey.USER.getValue());

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sf.format(startDate)+ " 00:00:00";
        String endDateStr = sf.format(endDate) + " 23:59:59";

        this.cusId = user.getCusId();
        this.startDate = startDateStr;
        this.endDate = endDateStr;
        this.page = page;
        this.rows = rows;
    };

    /**
     * get sql table by week
     */
    protected abstract IRecordBuilder<T> buildTablesByWeek(String tableName);

    /**
     * get sql table by month
     */
    protected abstract IRecordBuilder<T> buildTablesByMonth(String tableName);

    /**
     * get result count part table
     */
    protected abstract IRecordBuilder<T> buildCountByTable(IBaseService<T> baseService, String sql) throws Exception;

    /**
     * get result list part table
     */
    protected abstract IRecordBuilder<T> buildListByTable(IBaseService<T> baseService, String sql, Class<T> entityClazz) throws Exception;

    /**
     * get result count
     */
    protected abstract IRecordBuilder<T> buildCount(IBaseService<T> baseService, String sql) throws Exception;

    /**
     * get result list
     */
    protected abstract IRecordBuilder<T> buildList(IBaseService<T> baseService, String sql, Class<T> entityClazz) throws Exception;

    public RecordProduct getRecordProduct(){
        return  recordProduct;
    }
}
