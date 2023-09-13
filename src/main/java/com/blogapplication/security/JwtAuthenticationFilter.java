package com.blogapplication.security;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get token
        //Authorization is a key which will provide me the token

        System.out.println(request);
        String requestToken = request.getHeader("Authorization");

        System.out.println("Token : "+requestToken);
        //key starts with the Bearer ab79829798 like that
        String username = null;
        String token = null;

        if(requestToken!=null && requestToken.startsWith("Bearer "))
        {
            //it will provide the actual token without bearer
            token = requestToken.substring(7);

            try{
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Unable to get JWT token");
            }
            catch (ExpiredJwtException e)
            {
                System.out.println("token has been expired");
            }
            catch(MalformedJwtException e)
            {
                System.out.println("Invalid JWT exception");
            }
        }
        else
        {
            System.out.println("Either requestToken is null or token is not starting with Bearer");
        }

        //once we get the token now validate
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token, userDetails))
            {
                //token is correct

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, (Object) null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
            else
            {
                System.out.println("Invalid token");
            }
        }
        else
        {
            System.out.println("username is null or context is not null");
        }

        filterChain.doFilter(request, response);

    }
}
