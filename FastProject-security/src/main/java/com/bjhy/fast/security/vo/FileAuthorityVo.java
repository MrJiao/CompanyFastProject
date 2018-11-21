package com.bjhy.fast.security.vo;
import java.util.List;

/**
 * Create by: Jackson
 */
public class FileAuthorityVo {

    String ftpId;
    String userId;
    String roleId;
    String targetType;//Type.TARGET_TYPE_TO 等
    List<FileAuthority> FileAuthorityList;

    public static class FileAuthority{
        String path;
        int authority;
        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getAuthority() {
            return authority;
        }

        public void setAuthority(int authority) {
            this.authority = authority;
        }
    }

    public String getFtpId() {
        return ftpId;
    }

    public void setFtpId(String ftpId) {
        this.ftpId = ftpId;
    }

    public List<FileAuthority> getFileAuthorityList() {
        return FileAuthorityList;
    }

    public void setFileAuthorityList(List<FileAuthority> fileAuthorityList) {
        FileAuthorityList = fileAuthorityList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}
