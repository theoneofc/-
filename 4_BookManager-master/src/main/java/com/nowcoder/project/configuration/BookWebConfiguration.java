package com.nowcoder.project.configuration;

import com.nowcoder.project.interceptor.HostInfoInterceptor;
import com.nowcoder.project.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by nowcoder on 2018/08/07 下午4:19
 */
@Component
public class BookWebConfiguration implements WebMvcConfigurer {

  @Autowired
  private LoginInterceptor loginInterceptor;

  @Autowired
  private HostInfoInterceptor hostInfoInterceptor;

  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      /**
       * 添加拦截器
       */
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        //只要你发/**请求（任何请求）就会被hostInfoInterceptor拦截来坐坐
        //只要你发/books/**"请求（带/books前缀的请求）就会被loginInterceptor拦截来坐坐
        registry.addInterceptor(hostInfoInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/books/**");
      }
    };
  }

}


