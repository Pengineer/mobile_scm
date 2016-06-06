package hust.model;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {

	private static final long serialVersionUID = 9172525868222740330L;
	
	/**菜单类别：含子菜单*/
	public static final int MENUTYPE_PARENT = 1;
	
	/**菜单类别：最终菜单*/
	public static final int MENUTYPE_NODE = 2;
	
	/**菜单类别：分割线*/
	public static final int MENUTYPE_LINE = 3;
	
	private String id;
	private String name;
	private Integer type;
	private String typeName; // 数据库表中不含此字段，该属性值由type来决定
	private Action action;   // 根据需求而定
	private String pid;
	private Integer order;
	private String remark;
	
	private List<Menu> subMenuList; // 本菜单的子菜单
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		if (type != null) {
			switch (type) {
				case MENUTYPE_PARENT: this.typeName = "有子菜单"; break;
				case MENUTYPE_NODE: this.typeName = "无子菜单"; break;
				case MENUTYPE_LINE: this.typeName = "分割线"; break;
			}
		}
		this.type = type;
	}
	
	public List<Menu> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<Menu> subMenuList) {
		this.subMenuList = subMenuList;
	}
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
