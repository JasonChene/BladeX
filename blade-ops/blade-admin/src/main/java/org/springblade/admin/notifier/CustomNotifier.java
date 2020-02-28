/*
 *      Copyright (c) 2015-2025, Jeckxu All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the jeckxu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Jeckxu (chinajeckxu@163.com)
 */
package org.springblade.admin.notifier;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;


/**
 * DingTalk Notice
 * Can you find these packages ? , Plase Read DINGDING_README.md
 * {@code com.dingtalk.api.DefaultDingTalkClient}
 * {@code com.dingtalk.api.DingTalkClient}
 * {@code com.dingtalk.api.request.OapiRobotSendRequest}
 * {@code com.taobao.api.ApiException}
 *
 * @author jeckxu
 */
@Slf4j
public class CustomNotifier extends AbstractEventNotifier {

	/**
	 * massage template
	 */
	private static final String template = "服务名:%s(%s) n状态:%s(%s) n服务ip:%s";

	@Value("${spring.boot.admin.notify.dingtalk.webhook-token}")
	private String dingTalkToken;


	public CustomNotifier(InstanceRepository repository) {
		super(repository);
	}

	@Override
	protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
		return Mono.fromRunnable(() -> {
			if (event instanceof InstanceStatusChangedEvent) {
				log.info("Instance {} ({}) is {}", instance.getRegistration().getName(), event.getInstance(),
					((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());


				String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();
				String messageText = null;
				switch (status) {
					// 健康检查没通过
					case "DOWN":
						log.info("发送 健康检查没通过 的通知！");
						messageText = String.format(template, instance.getRegistration().getName(), event.getInstance(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "健康检查没通过", instance.getRegistration().getServiceUrl());
						this.sendMessage(messageText);
						break;
					// 服务离线
					case "OFFLINE":
						log.info("发送 服务离线 的通知！");
						messageText = String.format(template, instance.getRegistration().getName(), event.getInstance(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "服务离线", instance.getRegistration().getServiceUrl());
						this.sendMessage(messageText);
						break;
					//服务上线
					case "UP":
						log.info("发送 服务上线 的通知！");
						messageText = String.format(template, instance.getRegistration().getName(), event.getInstance(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "服务上线", instance.getRegistration().getServiceUrl());
						this.sendMessage(messageText);
						break;
					// 服务未知异常
					case "UNKNOWN":
						log.info("发送 服务未知异常 的通知！");
						messageText = String.format(template, instance.getRegistration().getName(), event.getInstance(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "服务未知异常", instance.getRegistration().getServiceUrl());
						this.sendMessage(messageText);
						break;
					default:
						break;
				}
			} else {
				log.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
					event.getType());
			}
		});
	}

	/**
	 * massage send
	 *
	 * @param messageText
	 */
	private void sendMessage(String messageText) {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + dingTalkToken);
		OapiRobotSendRequest request = new OapiRobotSendRequest();
		request.setMsgtype("text");
		OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
		text.setContent(messageText);
		request.setText(text);

		try {
			client.execute(request);
		} catch (ApiException e) {
			log.info("[ERROR] sendMessage", e);
		}
	}
}
