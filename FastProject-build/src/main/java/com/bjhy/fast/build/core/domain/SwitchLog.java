package com.bjhy.fast.build.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/** 
* @author welldo
* @version 2018年7月31日 上午11:36:26
*/

@Entity
public class SwitchLog {

	@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	@GeneratedValue(generator = "jpa-uuid")
	@Id
	private String id;
	
	//所属人, 只展示自己的任务...
    String userId;
	
	//任务名字
	private String taskName;
	
	private Date startTime;
	
	private Date endTime;
	
	//传输状态	    
    private Integer state;	//TransmitState
    
    //所属的结构化任务	(cascade= {CascadeType.REMOVE})
/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tableSwitchConfigId")
    TableSwitchConfig tableSwitchConfig;*/

    String tableSwitchConfigId;
    String fileSwitchConfigId;


    //所属的非结构化任务
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileSwitchConfigId")
	FileSwitchConfig fileSwitchConfig;*/

    private int type;//结构化和非结构类型 TYPE_TABLE TYPE_FILE

    //结构化
	public static final int TYPE_TABLE = 1;
	//非结构化
	public static final int TYPE_FILE = 2;

	//报错详情
	@Column(length = 300)
	private String resultMessage;
	
	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTableSwitchConfigId() {
		return tableSwitchConfigId;
	}

	public void setTableSwitchConfigId(String tableSwitchConfigId) {
		this.tableSwitchConfigId = tableSwitchConfigId;
	}

	public String getFileSwitchConfigId() {
		return fileSwitchConfigId;
	}

	public void setFileSwitchConfigId(String fileSwitchConfigId) {
		this.fileSwitchConfigId = fileSwitchConfigId;
	}
}
