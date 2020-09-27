package yibao.yiwei.common.build.confirm;

import yibao.yiwei.entity.DataConfirm;
import yibao.yiwei.exception.BuildProcessException;
import yibao.yiwei.service.IBaseService;

/**
 * 数据确认 
 * @description: 1.保存确认信息。2.通过条件查询确认信息
 * @author sunshy
 *
 */
public interface IConfirmDirector {
	/**
	 * 保存确认信息
	 * @param confirm
	 * @param dataConfirmService
	 * @param sql
	 * @return
	 * @throws IllegalAccessException
	 */
    ConfirmProduct saveConfirm(DataConfirm confirm, IBaseService<DataConfirm> dataConfirmService, String sql) throws IllegalAccessException;
    
    /**
     * 通过条件查询确认信息
     * @param dataConfirmService
     * @param sql
     * @return
     * @throws BuildProcessException
     * @throws IllegalAccessException
     */
    ConfirmProduct findConfirmByConditon(IBaseService<DataConfirm> dataConfirmService,String sql) throws BuildProcessException, IllegalAccessException;
}
