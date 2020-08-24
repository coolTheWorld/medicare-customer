package yibao.yiwei.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目常量,系统属性工具类
 * 
 * @author Administrator
 * 
 */
public class ProjectConstant {
	/**
	 * 常量:根据文件标识获取对应的文件类型
	 */
	public static final Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("102", "项目编码");
		map.put("202", "项目编码");
		map.put("303", "项目编码");
		map.put("103", "供应商/生产商信息");
		map.put("203", "供应商/生产商信息");
		map.put("304", "供应商/生产商信息");
		map.put("104", "医护人员信息");
		map.put("204", "医护人员信息");
		map.put("305", "医护人员信息");
		map.put("106", "入库信息");
		map.put("206", "入库信息");
		map.put("307", "入库信息");
		map.put("112", "出库信息");
		map.put("212", "出库信息");
		map.put("316", "出库信息");
		map.put("116", "库存信息");
		map.put("216", "库存信息");
		map.put("318", "库存信息");
		map.put("108", "销售信息");
		map.put("208", "销售信息");
		map.put("317", "销售信息");
		map.put("114", "库房信息");
		map.put("214", "库房信息");
		map.put("314", "库房信息");
		map.put("110", "处方信息");
		map.put("210", "处方信息");
		map.put("101", "分店信息");
		map.put("113", "分店信息");
		map.put("308", "住院记录");
		map.put("309", "出院记录");
		map.put("310", "医嘱信息");
		map.put("319", "医嘱执行记录");
		map.put("115", "科室信息");
		map.put("215", "科室信息");
		map.put("315", "科室信息");
		map.put("313", "诊断记录");
		map.put("325", "病案首页(概要)");
		map.put("326", "病案首页(出院诊断)");
		map.put("327", "病案首页(手术操作)");
		map.put("321", "检查、检验结果");
		map.put("322", "检查、检验结果详细");
	}
	
	/**
	 * 
	 * @param key
	 * @return 文件标识对应说明
	 */
	public static String getFileFlag(String key) {
		Map<String, String> flag = new HashMap<String, String>();
		flag.put("101", "单体药店 ");
		flag.put("102", "连锁药店");
		flag.put("201", "门诊");
		flag.put("301", "医院");
		return flag.get(key);
	}

	/**
	 * 获取系统属性
	 */
	public static void getProperty() {
		System.out.println("java_vendor:" + System.getProperty("java.vendor"));
		System.out.println("java_vendor_url:"+ System.getProperty("java.vendor.url"));
		System.out.println("java_home:" + System.getProperty("java.home"));
		System.out.println("java_class_version:"+ System.getProperty("java.class.version"));
		System.out.println("java_class_path:"+ System.getProperty("java.class.path"));
		System.out.println("os_name:" + System.getProperty("os.name"));
		System.out.println("os_arch:" + System.getProperty("os.arch"));
		System.out.println("os_version:" + System.getProperty("os.version"));
		System.out.println("user_name:" + System.getProperty("user.name"));
		System.out.println("user_home:" + System.getProperty("user.home"));
		System.out.println("user_dir:" + System.getProperty("user.dir"));
		System.out.println("java_vm_specification_version:"+ System.getProperty("java.vm.specification.version"));
		System.out.println("java_vm_specification_vendor:"+ System.getProperty("java.vm.specification.vendor"));
		System.out.println("java_vm_specification_name:"+ System.getProperty("java.vm.specification.name"));
		System.out.println("java_vm_version:"+ System.getProperty("java.vm.version"));
		System.out.println("java_vm_vendor:"+ System.getProperty("java.vm.vendor"));
		System.out.println("java_vm_name:" + System.getProperty("java.vm.name"));
		System.out.println("java_ext_dirs:"+ System.getProperty("java.ext.dirs"));
		System.out.println("file_separator:"+ System.getProperty("file.separator"));
		System.out.println("path_separator:"+ System.getProperty("path.separator"));
		System.out.println("line_separator:"+ System.getProperty("line.separator"));
	}

}
