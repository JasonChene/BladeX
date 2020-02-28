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
package org.springblade.auth.config;

import lombok.AllArgsConstructor;
import org.springblade.auth.granter.BladeTokenGranter;
import org.springblade.auth.props.AuthProperties;
import org.springblade.core.redis.cache.BladeRedisCache;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * 自定义TokenGranter配置类
 *
 * @author Chill
 */
@Configuration
@AllArgsConstructor
public class BladeTokenGranterConfiguration {

	private final DataSource dataSource;

	private AuthenticationManager authenticationManager;

	private UserDetailsService userDetailsService;

	private TokenStore tokenStore;

	private TokenEnhancer jwtTokenEnhancer;

	private JwtAccessTokenConverter jwtAccessTokenConverter;

	private AuthProperties authProperties;

	private IUserClient userClient;

	private BladeRedisCache redisCache;

	@Bean
	public BladeTokenGranter bladeTokenGranter() {
		return new BladeTokenGranter(dataSource, authenticationManager, userDetailsService, tokenStore, jwtTokenEnhancer, jwtAccessTokenConverter, authProperties, userClient, redisCache);
	}

}
