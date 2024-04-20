/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月14日 下午9:23:58
 */
package com.ruomm.springcloud.paymentserver.shiro.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ruomm.javax.jsonx.XJSON;
import com.ruomm.springcloud.paymentserver.jwt.utils.JWTUtils;
import com.ruomm.springcloud.paymentserver.utils.AppUtils;
import com.ruomm.springcloud.pojo.CommonResponse;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import java.io.IOException;

@Slf4j
public class UserAuthenticationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
		System.out.println("isAccessAllowed");
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("onAccessDenied");
		boolean tokenVerifyReuslt =verifyToken(request);
		if (tokenVerifyReuslt) {
			return true;
		} else {
			throwException(response, AppUtils.ERROR_TOKEN);
			return false;
		}

	}

	protected boolean verifyToken(ServletRequest request){
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest)request;
			String token=httpServletRequest.getHeader("Authorization");
			DecodedJWT decodedJWT = JWTUtils.verify(token);
			long userId = decodedJWT.getClaim("userId").asLong();
			String userName = decodedJWT.getClaim("userName").asString();
			if (userId>0 ) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}


	private void throwException(ServletResponse response, int errCode) throws IOException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
		httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
		CommonResponse resultJson = AppUtils.toNack(errCode);
		httpServletResponse.getWriter().write(XJSON.toJSONString(resultJson));
	}

	private void throwException(ServletResponse response, int errCode, String errMsg) throws IOException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
		httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
		CommonResponse resultJson = AppUtils.toNack(errCode,errMsg);
		httpServletResponse.getWriter().write(XJSON.toJSONString(response));
	}
}
