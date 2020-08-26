package yibao.yiwei.common.factory.record;

import yibao.yiwei.common.build.record.RecordProduct;
import yibao.yiwei.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface IRecordFactory<T> {
    //分表获取数据
    RecordProduct getRecordByPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;

    //分表查不到数据，再去总表查询数据
    RecordProduct getRecordForceByPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;

    //    //按月分表查不到数据，再去总表查询数据
//    RecordProduct getRecordForceByMonthPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;
//    //按月分表查不到数据，再去总表查询数据
//    RecordProduct getRecordForceByWeekPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;
//    //通过按月分表获取数据
//    RecordProduct getRecordByMonthPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;
//    //通过按周分表获取数据
//    RecordProduct getRecordByWeekPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;
    //通过总表获取数据
    RecordProduct getRecord(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;
}
