package yibao.yiwei.common.build.record;


import yibao.yiwei.exception.BuildProcessException;
import yibao.yiwei.service.IBaseService;

public class RecordDirector<T> implements IRecordDirector<T> {
    private IRecordBuilder<T> recordBuilder;

    public RecordDirector(IRecordBuilder recordBuilder) {
        this.recordBuilder = recordBuilder;
    }

    @Override
    public RecordProduct getResultByWeek(String tableName, IBaseService<T> baseService, String countSql,String sql, Class<T> entityClazz) throws BuildProcessException {
        return recordBuilder.buildTablesByWeek(tableName).buildCountByTable(baseService,countSql).buildListByTable(baseService,sql,entityClazz).getRecordProduct();
    }

    @Override
    public RecordProduct getResultByMonth(String tableName, IBaseService<T> baseService, String countSql,String sql, Class<T> entityClazz) throws BuildProcessException {
        return recordBuilder.buildTablesByMonth(tableName).buildCountByTable(baseService,countSql).buildListByTable(baseService,sql,entityClazz).getRecordProduct();
    }

    @Override
    public RecordProduct getResult(IBaseService<T> baseService,String countSql, String sql, Class<T> entityClazz) throws BuildProcessException {
        return recordBuilder.buildCount(baseService,countSql).buildList(baseService,sql,entityClazz).getRecordProduct();
    }

    @Override
    public RecordProduct getResultForceByMonth(String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws BuildProcessException {
        RecordProduct product = recordBuilder.buildTablesByMonth(tableName).buildCountByTable(baseService,countSql).getRecordProduct();
        if (product.getRecordCount() == 0){
            return recordBuilder.buildCount(baseService,countSql).buildList(baseService,sql,entityClazz).getRecordProduct();
        }
        return recordBuilder.buildTablesByMonth(tableName).buildCountByTable(baseService,countSql).buildListByTable(baseService,sql,entityClazz).getRecordProduct();
    }

    @Override
    public RecordProduct getResultForceByWeek(String tableName, IBaseService<T> baseService, String countSql, String sql, Class<T> entityClazz) throws BuildProcessException {
        RecordProduct product = recordBuilder.buildTablesByWeek(tableName).buildCountByTable(baseService,countSql).getRecordProduct();
        if (product.getRecordCount() == 0){
            return recordBuilder.buildCount(baseService,countSql).buildList(baseService,sql,entityClazz).getRecordProduct();
        }
        return recordBuilder.buildTablesByWeek(tableName).buildCountByTable(baseService,countSql).buildListByTable(baseService,sql,entityClazz).getRecordProduct();
    }
}
