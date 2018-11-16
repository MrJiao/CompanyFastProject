package com.bjhy.fast.build.core.vo;
import java.util.Date;

/**
 * 
 * @author welldo
 *2018年8月1日
 */
public class SwitchLogVo {

	private String id;
	
	//任务名字
	private String taskName;
	
	private Date startTime;
	
	private Date endTime;
	
	//传输状态	    
    private Integer state;	//TransmitState

    private int type;
    
  //报错详情
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
    
}
