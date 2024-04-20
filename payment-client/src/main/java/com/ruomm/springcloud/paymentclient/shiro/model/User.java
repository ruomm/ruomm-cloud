/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月14日 下午11:49:54
 */
package com.ruomm.springcloud.paymentclient.shiro.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 88005401113670980L;

	/**
	 *
	 */

	private String userId;

	private String token;

}
