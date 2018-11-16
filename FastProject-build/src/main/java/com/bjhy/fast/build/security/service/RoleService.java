package com.bjhy.fast.build.security.service;



import com.bjhy.tlevel.datax.common.infrastructure.BizCommonService;
import com.bjhy.fast.build.security.domain.Role;
import com.bjhy.fast.build.security.exception.RoleExitException;
import org.apel.gaia.commons.pager.PageBean;

import java.util.List;

/**
 * Create by: Jackson
 */
public interface RoleService extends BizCommonService<Role,String> {

    List<Role> findAll();

    Role findOne(String id);

    List<Role> findIn(String[] ids);

    PageBean queryNotIn(List<String> roleIds, PageBean pageBean);
    
    //新增角色
    void addOne(Role role) throws RoleExitException;

	List<Role> findAllRoleByOrg(String orgId);

    Role findByRoleCode(String roleCode);

	Role findByName(String name);
	
	//判断name 和 roleCode 两者是否都唯一  writeByWelldo
	boolean isExist(String roleCode, String name);
}
