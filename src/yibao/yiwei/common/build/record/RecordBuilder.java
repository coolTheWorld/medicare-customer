package yibao.yiwei.common.build.record;

import yibao.yiwei.common.ExceptionMessage;
import yibao.yiwei.service.IBaseService;
import yibao.yiwei.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

public class RecordBuilder extends IRecordBuilder {


    public RecordBuilder(HttpServletRequest request, Date startDate, Date endDate, String page, String rows) {
        super(request, startDate, endDate, page, rows);
    }

    @Override
    protected IRecordBuilder buildTablesByWeek(String tableName) {
        try {
            this.tableName = tableName;
            this.newTables = Utils.getTablesByWeek(tableName, this.startDate, this.endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    protected IRecordBuilder buildTablesByMonth(String tableName) {
        this.tableName = tableName;
        this.newTables = Utils.getTablesByMonth(tableName, this.startDate, this.endDate);
        return this;
    }

    @Override
    protected IRecordBuilder buildCountByTable(IBaseService baseService, String sql) throws Exception {
        if (tableName == null){
            throw new Exception(ExceptionMessage.TABLE_NAME_IS_NULL.getValue());
        }

        totals = baseService.findCountSqlByTable(sql, tableName, newTables, cusId, startDate,
                endDate);
        int total = 0;
        for (int i:totals){
            total += i;
        }
        recordProduct.setRecordCount(total);
        return this;
    }

    @Override
    protected IRecordBuilder buildListByTable(IBaseService baseService, String sql, Class entityClazz) throws Exception {
        if (tableName == null){
            throw new Exception(ExceptionMessage.TABLE_NAME_IS_NULL.getValue());
        }
        if(totals == null){
            throw new Exception(ExceptionMessage.TOTALS_IS_NULL.getValue());
        }
        recordProduct.setRecordList(baseService.findEntitySqlByTable(sql, entityClazz, rows, page, tableName,
                newTables, totals, cusId, startDate, endDate));
        return this;
    }

    @Override
    protected IRecordBuilder buildCount(IBaseService baseService, String sql) throws Exception {
        total = baseService.findCountSql(sql,cusId, startDate, endDate);
        recordProduct.setRecordCount(total);
        return this;
    }

    @Override
    protected IRecordBuilder buildList(IBaseService baseService, String sql, Class entityClazz) throws Exception {
//        if(total == 0){
//            throw new Exception("未调用buildCount方法或total变量为0");
//        }
        recordProduct.setRecordList(baseService.findByPageEntitySql(sql,entityClazz,rows,page,cusId, startDate, endDate));
        return this;
    }
}
