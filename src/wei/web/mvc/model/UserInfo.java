package wei.web.mvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.EntityResult;

/**
 * 浣跨敤娉ㄨВ褰㈠紡鐨刪ibernate瀹炰綋绫汇�鏈疄浣撶被涓嶄笌鏁版嵁搴撹〃杩涜鍏宠仈锛屼粎閫氳繃鍘熺敓鎬佺殑sql杩涜鏄犲皠銆�
 * @author wei
 * 2013-12-5
 */
@Entity
@SqlResultSetMapping(name="implicit",entities=@EntityResult(entityClass=UserInfo.class))
@NamedNativeQuery(name = "queryUserInfo", query = "select u.id,u.name,a.areaName,a.areaCode from "
		+ "userarea u left join areachina a on "
		+ "u.areaCode=a.areaCode where u.areaCode=:areaCode", resultSetMapping = "implicit")

public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = -2117530530819988235L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="areaName")
	private String areaName;

	@Column(name="areaCode")
	private int areaCode;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
