package yibao.yiwei.common.factory.record;

import yibao.yiwei.common.build.record.RecordProduct;
import yibao.yiwei.exception.BuildProcessException;
import yibao.yiwei.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * record factory to get record data
 * @author sunshy
 * @param <T>
 */
public interface IRecordFactory<T> {
    /**
     * 分表获取数据
     * @param request
     * @param startDate
     * @param endDate
     * @param page
     * @param rows
     * @param tableName
     * @param baseService
     * @param countSql
     * @param sql
     * @param entityClazz
     * @return
     * @throws Exception
     */
    RecordProduct getRecordByPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws BuildProcessException;

    /**
     * 分表查不到数据，再去总表查询数据
     * @param request
     * @param startDate
     * @param endDate
     * @param page
     * @param rows
     * @param tableName
     * @param baseService
     * @param countSql
     * @param sql
     * @param entityClazz
     * @return
     * @throws Exception
     */
    RecordProduct getRecordForceByPart(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws BuildProcessException;

    /**
     * 通过总表获取数据
     * @param request
     * @param startDate
     * @param endDate
     * @param page
     * @param rows
     * @param baseService
     * @param countSql
     * @param sql
     * @param entityClazz
     * @return
     * @throws Exception
     */
    RecordProduct getRecord(HttpServletRequest request, Date startDate, Date endDate, String page, String rows, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws BuildProcessException;
}
