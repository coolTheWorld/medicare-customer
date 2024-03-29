package yibao.yiwei.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yibao.yiwei.dao.IBaseDao;
import yibao.yiwei.service.IUploadfileManager;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class UploadfileManager implements IUploadfileManager {
	
	@Autowired
	private IBaseDao baseDao;
	
	// 根据日期天数与文件类型102 获得 当天解析出该类型文件的cusID 集合 *未应用*
	@Override
	public List getAllcusIds(String days, String orgId) throws ParseException {
		List list = new ArrayList();
		Date today = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		gc.setTime(today);
		gc.add(5, -Integer.parseInt(days));// 表示天数减一
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		String todayStr = sf.format(today);
		String dateStr = sf.format(gc.getTime());
		today = sf.parse(todayStr);
		Date date = sf.parse(dateStr);
		String hql;
		if (Integer.parseInt(days) == 1) { // 表示取当天
			if (orgId.equals("000000")) {// 总管理员id
				hql = "select u.cusId from Uploadfile u where (u.upFileflag='102' or u.upFileflag='302') and u.upCollectiondate=?0 group by u.cusId";
				list = baseDao.find(hql, today);

			} else {
				hql = "select u.cusId from Uploadfile u ,CusorgRelate r where u.upFileflag='102' and u.upCollectiondate=?0 "
						+ "and u.cusId=r.cusId and r.orgId=?1 group by u.cusId";
				list = baseDao.find(hql, today, orgId);

			}
			// 早的时间在前 晚的时间在后

		} else {// 一周或一月
			if (orgId.equals("000000")) {
				hql = "select u.cusId from Uploadfile u where (u.upFileflag='102' or u.upFileflag='302') and u.upCollectiondate between ?0 and ?1 group by u.cusId";
				list = baseDao.find(hql, date, today);

			} else {
				hql = "select u.cusId from Uploadfile u ,CusorgRelate r where (u.upFileflag='102' or u.upFileflag='302') and u.upCollectiondate between ?0 and ?1 "
						+ "and u.cusId=r.cusId and r.orgId=?2 group by u.cusId";
				list = baseDao.find(hql, date, today, orgId);
			}
		}
		return list;
	}
}
