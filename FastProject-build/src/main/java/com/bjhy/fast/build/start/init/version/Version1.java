package com.bjhy.fast.build.start.init.version;

import com.bjhy.fast.build.start.init.core.VersionInf;
import com.bjhy.tlevel.datax.common.Type;

import com.bjhy.fast.security.dao.RoleRepository;
import com.bjhy.fast.security.dao.SystemMenuRepository;
import com.bjhy.fast.security.dao.UserInfoRepository;
import com.bjhy.fast.security.domain.Role;
import com.bjhy.fast.security.domain.SystemMenu;
import com.bjhy.fast.security.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by: Jackson
 */
@Component
public class Version1 implements VersionInf {

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void init() {

        new InitData().init();
    }

    @Override
    public boolean isEnable() {
        return true;
    }




    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SystemMenuRepository systemMenuRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserInfoRepository userInfoRepository;
    public class InitData{

        Logger logger = LoggerFactory.getLogger(getClass());

        List<SystemMenu> userMenu = new ArrayList<>();
        List<SystemMenu> adminMenu = new ArrayList<>();
        List<Role> roles;

        Role normalRole;
        Role adminRole;
        void init(){
            systemMenu();
            roles = role();
            userinfo("admin");
        }

        private UserInfo userinfo(String username) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.getRoleList().add(adminRole);
            userInfo.setPassword(passwordEncoder.encode("123"));
            userInfo.setCreateDate(new Date());
            userInfo.setInit(true);
            userInfoRepository.save(userInfo);
            return userInfo;
        }

        private List<Role> role() {
            List<Role> roles = new ArrayList<>();

            adminRole = new Role();
            adminRole.setName("admin");
            adminRole.setRoleCode(Type.ADMIN_ROLE_CODE);
            adminRole.setCreateDate(new Date());
            adminRole.getSystemMenuList().addAll(userMenu);
            adminRole.getSystemMenuList().addAll(adminMenu);
            adminRole.setInit(true);
            roleRepository.save(adminRole);

            normalRole = new Role();
            normalRole.setName("normal");
            normalRole.setRoleCode(Type.NORMAL_ROLE_CODE);
            normalRole.setCreateDate(new Date());
            normalRole.getSystemMenuList().addAll(userMenu);
            normalRole.setInit(true);
            roleRepository.save(normalRole);
            
            roles.add(adminRole);
            roles.add(normalRole);
            return roles;
        }

        public void systemMenu(){
            userMenu();
            //adminMenu();
        }

        private void userMenu() {

            //1.参数设置-------------------------------------------------------
            SystemMenu paramSetting = new SystemMenu();
            paramSetting.setMenuName("转监狱");
            paramSetting.setPxNum(1);
            paramSetting.setMenuType(SystemMenu.MENU_TYPE_URL);
            paramSetting.setMenuValue("test/page1");
            systemMenuRepository.save(paramSetting);
            userMenu.add(paramSetting);

            SystemMenu paramSetting2 = new SystemMenu();
            paramSetting2.setMenuName("监狱局");
            paramSetting2.setPxNum(2);
            paramSetting2.setMenuType(SystemMenu.MENU_TYPE_URL);
            paramSetting2.setMenuValue("eventSource/from");
            systemMenuRepository.save(paramSetting2);
            userMenu.add(paramSetting2);

            SystemMenu paramSetting3 = new SystemMenu();
            paramSetting3.setMenuName("修改");
            paramSetting3.setPxNum(3);
            paramSetting3.setMenuType(SystemMenu.MENU_TYPE_URL);
            paramSetting3.setMenuValue("eventSource/from");
            systemMenuRepository.save(paramSetting3);
            userMenu.add(paramSetting3);


          /*  SystemMenu structure_from = new SystemMenu();
            structure_from.setPxNum(paramSetting.getPxNum()*10+1);
            structure_from.setMenuName("结构化源设置");
            structure_from.setParent(paramSetting);
            structure_from.setMenuType(SystemMenu.MENU_TYPE_URL);
            structure_from.setMenuValue("eventSource/from");
            systemMenuRepository.save(structure_from);
            userMenu.add(structure_from);

            SystemMenu structure_target = new SystemMenu();
            structure_target.setPxNum(paramSetting.getPxNum()*10+2);
            structure_target.setMenuName("结构化目标设置");
            structure_target.setParent(paramSetting);
            structure_target.setMenuType(SystemMenu.MENU_TYPE_URL);
            structure_target.setMenuValue("eventSource/target");
            systemMenuRepository.save(structure_target);
            userMenu.add(structure_target);


*//*            SystemMenu unstructure = new SystemMenu();
            unstructure.setPxNum(paramSetting.getPxNum()*10+3);
            unstructure.setMenuName("非结构化参数设置");
            unstructure.setParent(paramSetting);
            unstructure.setMenuType(SystemMenu.MENU_TYPE_URL);
            unstructure.setMenuValue("fileSwitchConfig/index");
            systemMenuRepository.save(unstructure);
            userMenu.add(unstructure);*//*

            //2.数据交换-------------------------------------------------------
            SystemMenu dataSwitch = new SystemMenu();
            dataSwitch.setMenuName("数据交换");
            dataSwitch.setPxNum(2);
            dataSwitch.setMenuType(SystemMenu.MENU_TYPE_DIRECTORY);
            systemMenuRepository.save(dataSwitch);
            userMenu.add(dataSwitch);

            SystemMenu structureSwitch = new SystemMenu();
            structureSwitch.setPxNum(dataSwitch.getPxNum()*10+1);
            structureSwitch.setMenuName("结构化交换");
            structureSwitch.setParent(dataSwitch);
            structureSwitch.setMenuType(SystemMenu.MENU_TYPE_URL);
            structureSwitch.setMenuValue("eventSource/structure_switch");
            systemMenuRepository.save(structureSwitch);
            userMenu.add(structureSwitch);*/

        }

        public void adminMenu(){
            //3.用户管理-------------------------------------------------------
            SystemMenu userMenu = new SystemMenu();
            userMenu.setMenuName("用户管理");
            userMenu.setPxNum(3);
            userMenu.setMenuType(SystemMenu.MENU_TYPE_DIRECTORY);
            systemMenuRepository.save(userMenu);
            adminMenu.add(userMenu);

            SystemMenu userInfoMenu = new SystemMenu();
            userInfoMenu.setPxNum(userMenu.getPxNum()*10+1);
            userInfoMenu.setMenuName("用户账号管理");
            userInfoMenu.setParent(userMenu);
            userInfoMenu.setMenuType(SystemMenu.MENU_TYPE_URL);
            userInfoMenu.setMenuValue("userInfo/index");
            systemMenuRepository.save(userInfoMenu);
            adminMenu.add(userInfoMenu);


            SystemMenu organization = new SystemMenu();
            organization.setPxNum(userMenu.getPxNum()*10+5);
            organization.setMenuName("组织机构管理");
            organization.setParent(userMenu);
            organization.setMenuType(SystemMenu.MENU_TYPE_URL);
            organization.setMenuValue("organization/index");
            systemMenuRepository.save(organization);
            adminMenu.add(organization);

            SystemMenu role = new SystemMenu();
            role.setPxNum(userMenu.getPxNum()*10+6);
            role.setMenuName("角色管理");
            role.setParent(userMenu);
            role.setMenuType(SystemMenu.MENU_TYPE_URL);
            role.setMenuValue("role/index");
            systemMenuRepository.save(role);
            adminMenu.add(role);

            //4.交换配置管理-----------------------------------------------------
            SystemMenu dataBaseMenu = new SystemMenu();
            dataBaseMenu.setMenuName("交换配置管理");
            dataBaseMenu.setPxNum(4);
            dataBaseMenu.setMenuType(SystemMenu.MENU_TYPE_DIRECTORY);
            systemMenuRepository.save(dataBaseMenu);
            adminMenu.add(dataBaseMenu);

            SystemMenu dbMenu = new SystemMenu();
            dbMenu.setPxNum(dataBaseMenu.getPxNum()*10+1);
            dbMenu.setMenuName("数据库账号管理");
            dbMenu.setParent(dataBaseMenu);
            dbMenu.setMenuType(SystemMenu.MENU_TYPE_URL);
            dbMenu.setMenuValue("db/index");
            systemMenuRepository.save(dbMenu);
            adminMenu.add(dbMenu);

            SystemMenu filterSuffix = new SystemMenu();
            filterSuffix.setPxNum(dataBaseMenu.getPxNum()*10+2);
            filterSuffix.setMenuName("非结构化过滤条件");
            filterSuffix.setParent(dataBaseMenu);
            filterSuffix.setMenuType(SystemMenu.MENU_TYPE_URL);
            filterSuffix.setMenuValue("filterSuffix/index");
            systemMenuRepository.save(filterSuffix);
            adminMenu.add(filterSuffix);
            //6.日志管理---------------------------------------------------------
            SystemMenu logMenu = new SystemMenu();
            logMenu.setMenuName("日志管理");
            logMenu.setPxNum(6);
            logMenu.setMenuType(SystemMenu.MENU_TYPE_DIRECTORY);
            systemMenuRepository.save(logMenu);
            adminMenu.add(logMenu);

            SystemMenu logMenuLook = new SystemMenu();
            logMenuLook.setPxNum(logMenu.getPxNum()*10+1);
            logMenuLook.setMenuName("日志查看");
            logMenuLook.setParent(logMenu);
            logMenuLook.setMenuType(SystemMenu.MENU_TYPE_URL);
            logMenuLook.setMenuValue("controllerLog/index");
            systemMenuRepository.save(logMenuLook);
            adminMenu.add(logMenuLook);
        }

    }
}
