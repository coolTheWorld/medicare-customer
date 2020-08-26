package yibao.yiwei.common.build.record;

import yibao.yiwei.service.IBaseService;

/**
 * @author sunshy
 * direct the record builder
 * @param <T>
 */
public interface IRecordDirector<T> {

    /**
     * get result by week part of table
     * @param tableName
     * @param baseService
     * @param countSql
     * @param sql
     * @param entityClazz
     * @return
     * @throws Exception
     */
    RecordProduct getResultByWeek(String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;

    /**
     * get result by month part of table
     * @param tableName
     * @param baseService
     * @param countSql
     * @param sql
     * @param entityClazz
     * @return
     * @throws Exception
     */
    RecordProduct getResultByMonth(String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;

    /**
     * get result by ancestry table
     * @param baseService
     * @param countSql
     * @param sql
     * @param entityClazz
     * @return
     * @throws Exception
     */
    RecordProduct getResult(IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;

    /**
     * get result by month part of table,
     * if result is null,the get result by ancestry table
     * @param tableName
     * @param baseService
     * @param countSql
     * @param sql
     * @param entityClazz
     * @return
     * @throws Exception
     */
    RecordProduct getResultForceByMonth(String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;

    /**
     * get result by week part of table,
     * if result is null,the get result by ancestry table
     * @param tableName
     * @param baseService
     * @param countSql
     * @param sql
     * @param entityClazz
     * @return
     * @throws Exception
     */
    RecordProduct getResultForceByWeek(String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws Exception;


}
