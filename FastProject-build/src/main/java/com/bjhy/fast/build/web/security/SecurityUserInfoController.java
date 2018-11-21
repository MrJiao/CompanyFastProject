package com.bjhy.fast.build.web.security;


import com.bjhy.fast.build.core.log.LogDescribe;
import com.bjhy.fast.build.core.log.LogIgnore;
import com.bjhy.fast.build.core.utils.ConvertUtil;
import com.bjhy.fast.security.domain.UserInfo;
import com.bjhy.fast.security.exception.RegisterException;
import com.bjhy.fast.security.exception.UserExitException;
import com.bjhy.fast.security.service.SecurityUserInfoService;
import com.bjhy.fast.security.vo.UserInfoVo;
import org.apel.gaia.commons.i18n.Message;
import org.apel.gaia.commons.i18n.MessageUtil;
import org.apel.gaia.commons.jqgrid.QueryParams;
import org.apel.gaia.commons.pager.Condition;
import org.apel.gaia.commons.pager.Operation;
import org.apel.gaia.commons.pager.PageBean;
import org.apel.gaia.commons.pager.RelateType;
import org.apel.gaia.util.jqgrid.JqGridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Create by: Jackson
 */
@Controller
@RequestMapping(value = "/userInfo")
public class SecurityUserInfoController {

    private final static String INDEX_URL = "userManager/userInfo_index";

    @Autowired
    private SecurityUserInfoService userInfoService;

    @LogIgnore
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(){
        return INDEX_URL;
    }

    @LogDescribe("查看全部用户")
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody    PageBean list(QueryParams queryParams){
        PageBean pageBean = JqGridUtil.getPageBean(queryParams);
        userInfoService.pageQueryExcludeAdmin(pageBean);
        List<UserInfoVo> userInfoVoList = ConvertUtil.convertUserInfoList((List)pageBean.getItems());
        pageBean.setItems(userInfoVoList);
        return pageBean;
    }

    //新增

    @LogDescribe("新增用户")
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Message create(UserInfoVo vo,HttpServletResponse response){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(vo.getUsername());
        userInfo.setPassword(vo.getPassword());
        userInfo.setNickName(vo.getNickName());
        try {
            userInfoService.register(userInfo);
        } catch (UserExitException e) {
            response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
            return MessageUtil.message(405,"userInfo.account.exit.error");
        } catch (RegisterException e) {
            return MessageUtil.message(405,"userInfo.register.error");
        }
        return MessageUtil.message("common.create.success");
    }

    @LogDescribe("更新用户")

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Message create(@PathVariable String id, UserInfo userInfo){
        userInfo.setId(id);
        //userinfo 目前只用password 和nickName 其他字段未使用
        userInfoService.updateUserEncodePwd(userInfo);
        return MessageUtil.message("common.update.success");
    }

    @LogDescribe("修改自己的密码")

    @RequestMapping(value = "/pwd", method = RequestMethod.PUT)
    public @ResponseBody Message pwd(String password){
        userInfoService.updateEncodePwd(password);
        return MessageUtil.message("common.update.success");
    }

    @LogDescribe("修改自己的昵称")

    @RequestMapping(value = "/nickname", method = RequestMethod.PUT)
    public @ResponseBody Message nickName(String nickname){
        userInfoService.updateNickName(nickname);
        return MessageUtil.message("common.update.success");
    }

    @LogDescribe("查看用户资料")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody UserInfo view(@PathVariable String id){ return userInfoService.findById(id); }

    @LogDescribe("批量删除用户")
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Message batchDelete(@RequestParam("ids[]") String[] ids){
    	//先判断对象是否为系统初始化创建的, 如果是, 则无法删除
    	for (String id : ids) {
    		UserInfo userInfo = userInfoService.findById(id);
    		if(userInfo.isInit()) {
    			return MessageUtil.message(405,"role.delete.init.error");
    		}
		}
        userInfoService.deleteById(ids);
        return MessageUtil.message("common.delete.success");
    }

    @LogDescribe("查询全部用户")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody  List<UserInfo> getAll(){
        return userInfoService.findAll(new Sort(Sort.Direction.DESC, "createDate"));
    }

    @LogDescribe("根据标识字段查询数据")
    @RequestMapping(value = "/specifiedFieldQuery", method = RequestMethod.GET)
    public @ResponseBody List<UserInfo> specifiedFieldQuery(String id){
        Condition condition = new Condition();
        condition.setRelateType(RelateType.AND);
        condition.setOperation(Operation.CN);
        condition.setPropertyName("id");
        condition.setPropertyValue(id);
        return userInfoService.findByCondition(condition);
    }

}
