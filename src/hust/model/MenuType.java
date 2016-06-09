package hust.model;

import java.io.Serializable;

public class MenuType implements Serializable{

	private static final long serialVersionUID = 966945310095871183L;
	
	private int typeId;
	private String typeName;
	
	public MenuType() {}
	
	public MenuType(int typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
