package com.bjhy.fast.build.core.vo;

/**
 * Create by: Jackson
 */
public class TableAuthorityVo {

	private String tableName;//表名
    
	private String comment;
    
	private String type;//TABLE,VIEW
    
	private String owner; //当前表的所属者

	private boolean checked;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

    
}
