/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.system.feign;

import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign接口类
 *
 * @author Chill
 */
@FeignClient(
	value = AppConstant.APPLICATION_SYSTEM_NAME,
	fallback = ISysClientFallback.class
)
public interface ISysClient {

	String API_PREFIX = "/client";
	String MENU = API_PREFIX + "/menu";
	String DEPT = API_PREFIX + "/dept";
	String DEPT_NAME = API_PREFIX + "/dept-name";
	String DEPT_NAMES = API_PREFIX + "/dept-names";
	String DEPT_CHILD = API_PREFIX + "/dept-child";
	String ROLE = API_PREFIX + "/role";
	String ROLE_NAME = API_PREFIX + "/role-name";
	String ROLE_NAMES = API_PREFIX + "/role-names";
	String ROLE_ALIAS = API_PREFIX + "/role-alias";
	String ROLE_ALIASES = API_PREFIX + "/role-aliases";
	String TENANT = API_PREFIX + "/tenant";
	String TENANT_ID = API_PREFIX + "/tenant-id";
	String PARAM = API_PREFIX + "/param";
	String PARAM_VALUE = API_PREFIX + "/param-value";

	/**
	 * 获取菜单
	 *
	 * @param id 主键
	 * @return Menu
	 */
	@GetMapping(MENU)
	R<Menu> getMenu(Long id);

	/**
	 * 获取部门
	 *
	 * @param id 主键
	 * @return Dept
	 */
	@GetMapping(DEPT)
	R<Dept> getDept(@RequestParam("id") Long id);

	/**
	 * 获取部门名
	 *
	 * @param id 主键
	 * @return 部门名
	 */
	@GetMapping(DEPT_NAME)
	R<String> getDeptName(@RequestParam("id") Long id);

	/**
	 * 获取角色
	 *
	 * @param id 主键
	 * @return Role
	 */
	@GetMapping(ROLE)
	R<Role> getRole(@RequestParam("id") Long id);

	/**
	 * 获取角色名
	 *
	 * @param id 主键
	 * @return 角色名
	 */
	@GetMapping(ROLE_NAME)
	R<String> getRoleName(@RequestParam("id") Long id);

	/**
	 * 获取角色别名
	 *
	 * @param id 主键
	 * @return 角色别名
	 */
	@GetMapping(ROLE_ALIAS)
	R<String> getRoleAlias(@RequestParam("id") Long id);

	/**
	 * 获取部门名
	 *
	 * @param deptIds 主键
	 * @return
	 */
	@GetMapping(DEPT_NAMES)
	R<List<String>> getDeptNames(@RequestParam("deptIds") String deptIds);

	/**
	 * 获取子部门ID
	 *
	 * @param deptId
	 * @return
	 */
	@GetMapping(DEPT_CHILD)
	R<List<Dept>> getDeptChild(@RequestParam("deptId") Long deptId);

	/**
	 * 获取角色名
	 *
	 * @param roleIds 主键
	 * @return
	 */
	@GetMapping(ROLE_NAMES)
	R<List<String>> getRoleNames(@RequestParam("roleIds") String roleIds);

	/**
	 * 获取角色别名
	 *
	 * @param roleIds 主键
	 * @return 角色别名
	 */
	@GetMapping(ROLE_ALIASES)
	R<List<String>> getRoleAliases(@RequestParam("roleIds") String roleIds);

	/**
	 * 获取租户
	 *
	 * @param id 主键
	 * @return Tenant
	 */
	@GetMapping(TENANT)
	R<Tenant> getTenant(@RequestParam("id") Long id);

	/**
	 * 获取租户
	 *
	 * @param tenantId 租户id
	 * @return Tenant
	 */
	@GetMapping(TENANT_ID)
	R<Tenant> getTenant(@RequestParam("tenantId") String tenantId);

	/**
	 * 获取参数
	 *
	 * @param id 主键
	 * @return Param
	 */
	@GetMapping(PARAM)
	R<Param> getParam(@RequestParam("id") Long id);

	/**
	 * 获取参数配置
	 *
	 * @param paramKey 参数key
	 * @return String
	 */
	@GetMapping(PARAM_VALUE)
	R<String> getParamValue(@RequestParam("paramKey") String paramKey);

}
