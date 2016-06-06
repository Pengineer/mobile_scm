package hust.model;

import java.io.Serializable;

public class UserRole implements Serializable {

	private static final long serialVersionUID = -1809696910499886195L;
	
	private String id;
	private String uid;
	private String cid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
}
