package hust.model;

import java.io.Serializable;

public class Action implements Serializable {

	private static final long serialVersionUID = -5226269027036266539L;
	
	/**动作类型：普通动作*/
	public static final int ACTIONTYPE_NORMAL = 1;
	
	/**动作类型：普通动作*/
	public static final int ACTIONTYPE_AUTHOR = 2;
	
	private String id;
	private String name;
	private Integer type;
	private String mid;
	private String remark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
