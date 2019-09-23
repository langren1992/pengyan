package com.anserx.pengyan.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.anserx.pengyan.common.Result;
import com.anserx.pengyan.common.ResultEnum;
import com.anserx.pengyan.constants.SecurityConstants;
import com.anserx.pengyan.oauth.api.OauthAccessApi;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务权限校验过滤器
 */
@Component
public class AccessFilter extends ZuulFilter {

    @Reference
    private OauthAccessApi oauthAccessApi;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println(request.getRequestURI());
        //需要权限校验URL
        if ("/uaa/oauth/token".equalsIgnoreCase(request.getRequestURI())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        // 设置响应乱码问题
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");
        //token对象,有可能在请求头传递过来，也有可能是通过参数传过来，实际开发一般都是请求头方式
        String token = request.getHeader("access_token");
        if (StringUtils.isBlank((token))) {
            token = request.getParameter("access_token");
        }
        //登录校验逻辑  如果token为null，则直接返回客户端，而不进行下一步接口调用
        if (StringUtils.isBlank(token)) {
            // 过滤该请求，不对其进行路由
            requestContext.setSendZuulResponse(false);
            // 返回错误代码
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            // 返回错误消息
            requestContext.setResponseBody(JSONObject.toJSONString(Result.error(ResultEnum.CODE_401)));
        }

        Result result = oauthAccessApi.accessCheck(token);
        if (result.getCode() != 200){
            // 过滤该请求，不对其进行路由
            requestContext.setSendZuulResponse(false);
            // 返回错误代码
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            // 返回错误消息
            requestContext.setResponseBody(JSONObject.toJSONString(result));
        } else {
            requestContext.addZuulRequestHeader(SecurityConstants.USER_HEADER, JSONObject.toJSONString(result.getData()));
        }
        return null;
    }
}
