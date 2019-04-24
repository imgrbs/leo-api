package app.leo.matching.interceptors;

import app.leo.matching.DTO.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    private String SECRET;

    public TokenInterceptor(String secret) {
        this.SECRET = secret;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (this.isOptionMethod(request)) {
            return true;
        }
        String token = getToken(request);
        User user = this.getUserFromToken(token);
        request.setAttribute("user", user);
        return true;
    }

    private boolean isValidToken (String token){
        if (token == null) {
            return false;
        } else if (token.startsWith("Bearer") == false || token.equals("")) {
            return false;
        }
        return true;
    }

    private String getToken (HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        return token;
    }

    private User getUserFromToken(String token) {
        String tokenFormat = token.substring(7);

        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(this.SECRET)
                .parseClaimsJws(tokenFormat);

        long userId = Long.parseLong((String) claims.getBody().get("userId"));
        String role = (String) claims.getBody().get("role");

        return new User(userId, role);
    }

    private boolean isOptionMethod(HttpServletRequest request) {
        return "OPTIONS".equalsIgnoreCase(request.getMethod());
    }
}