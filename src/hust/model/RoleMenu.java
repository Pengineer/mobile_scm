package hust.model;

import java.io.Serializable;

public class RoleMenu implements Serializable {

	private static final long serialVersionUID = -8301519376798181286L;
	
	private String id;
	private String rid;
	private String mid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
}
