package yibao.yiwei.common.build.confirm;

import yibao.yiwei.entity.DataConfirm;
import yibao.yiwei.exception.BuildProcessException;
import yibao.yiwei.service.IBaseService;

public interface IConfirmDirector {
    ConfirmProduct saveConfirm(DataConfirm confirm, IBaseService<DataConfirm> dataConfirmService, String sql) throws IllegalAccessException;
    ConfirmProduct findConfirmByConditon(IBaseService<DataConfirm> dataConfirmService,String sql) throws BuildProcessException, IllegalAccessException;
}
