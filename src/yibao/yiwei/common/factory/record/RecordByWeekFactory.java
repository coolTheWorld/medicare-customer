package yibao.yiwei.common.factory.record;

import yibao.yiwei.common.build.record.*;
import yibao.yiwei.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class RecordByWeekFactory<T> implements IRecordFactory<T> {
    @Override
    public RecordProduct getRecordForceByPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService baseService, String countSql, String sql, Class entityClazz) throws Exception {
        IRecordBuilder<T> recordBuilder = new RecordBuilder(request, startDate, endDate,page,rows);
        IRecordDirector<T> recordDirector = new RecordDirector(recordBuilder);
        return recordDirector.getResultForceByWeek(tableName, baseService, countSql, sql, entityClazz);
    }

    @Override
    public RecordProduct getRecordByPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService baseService, String countSql, String sql, Class entityClazz) throws Exception {
        IRecordBuilder<T> recordBuilder = new RecordBuilder(request, startDate, endDate,page,rows);
        IRecordDirector<T> recordDirector = new RecordDirector(recordBuilder);
        return recordDirector.getResultByWeek(tableName, baseService, countSql, sql, entityClazz);
    }

    @Override
    public RecordProduct getRecord(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, IBaseService baseService, String countSql, String sql, Class entityClazz) throws Exception{
        IRecordBuilder<T> recordBuilder = new RecordBuilder(request, startDate, endDate,page,rows);
        IRecordDirector<T> recordDirector = new RecordDirector(recordBuilder);
        return recordDirector.getResult(baseService, countSql, sql, entityClazz);
    }
}
