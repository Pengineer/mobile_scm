package hust.model;

import java.io.Serializable;

public class RoleAction implements Serializable {

	private static final long serialVersionUID = 2372443219200501380L;
	
	private String id;
	private String rid;
	private String aid;
	
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
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	
}
