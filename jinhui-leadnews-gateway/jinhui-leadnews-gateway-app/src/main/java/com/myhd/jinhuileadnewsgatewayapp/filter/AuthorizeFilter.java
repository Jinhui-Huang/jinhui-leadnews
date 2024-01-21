package com.myhd.jinhuileadnewsgatewayapp.filter;

import com.myhd.jinhuileadnewsutils.utils.common.AppJwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
/**
 * Description: AuthorizeFilter
 * <br></br>
 * className: AuthorizeFilter
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsgateway.filter
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/2 23:38
 */

@Component
public class AuthorizeFilter implements Ordered, GlobalFilter {
    private static final Logger log = LoggerFactory.getLogger(AuthorizeFilter.class);

    public AuthorizeFilter() {
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if (request.getURI().getPath().contains("/login")) {
            return chain.filter(exchange);
        } else {
            String token = request.getHeaders().getFirst("token");
            if (StringUtils.isBlank(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            } else {
                try {
                    Claims claimsBody = AppJwtUtil.getClaimsBody(token);
                    int result = AppJwtUtil.verifyToken(claimsBody);
                    if (result == 1 || result == 2) {
                        response.setStatusCode(HttpStatus.UNAUTHORIZED);
                        return response.setComplete();
                    }
                } catch (Exception var8) {
                    var8.printStackTrace();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                return chain.filter(exchange);
            }
        }
    }

    public int getOrder() {
        return 0;
    }
}
