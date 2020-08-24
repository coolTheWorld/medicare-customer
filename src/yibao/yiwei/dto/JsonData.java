package yibao.yiwei.dto;

import java.util.Arrays;
import java.util.List;

public class JsonData {
	
	private String name;
	private String[] xcontent;
	private Integer[] data;
	private List list1;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getXcontent() {
		return xcontent;
	}
	public void setXcontent(String[] xcontent) {
		this.xcontent = xcontent;
	}
	public Integer[] getData() {
		return data;
	}
	public void setData(Integer[] data) {
		this.data = data;
	}
	public List getList1() {
		return list1;
	}
	public void setList1(List list1) {
		this.list1 = list1;
	}
	@Override
	public String toString() {
		return "JsonData [name=" + name + ", xcontent=" + Arrays.toString(xcontent) + ", data=" + Arrays.toString(data)
				+ ", list1=" + list1 + "]";
	}
	
	
	
}
