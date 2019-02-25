package com.n.ysb.service.system.vo;


public class NnRoleVo {

    private String roleCode;
    private String roleName;
    private String roleStatus;
    
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
    public String getRoleStatus() {
        return roleStatus;
    }
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus == null ? null : roleStatus.trim();
    }
    
}