package kr.co.velysound.config.web;

import kr.co.velysound.config.interceptor.CommonInterceptor;
import kr.co.velysound.config.interceptor.HistoryInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.util.Arrays;
import java.util.List;

/**
 * 스프링 웹 설정
 *
 * @author 오세진
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final CommonInterceptor commonInterceptor;
	private final HistoryInterceptor historyInterceptor;

	private List<String> excludeUriPatter = Arrays.asList("/assets/**", "/error/**", "/login.do", "/actionLogin.do", "/logout.do");

	/**
	 * 타일즈 설정
	 * setDefinitions : 타일즈 설정 경로지정
	 * setCheckRefresh : 새로고침 가능 여부
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[]{"/WEB-INF/tiles/tiles.xml"});
		configurer.setCheckRefresh(true);
		return configurer;
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	/**
	 * 정적 리소스 관리
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}

	/**
	 * 인터셉터 등록
	 * 정적경로 무시
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(commonInterceptor)
//				.addPathPatterns("/**")
//				.excludePathPatterns(excludeUriPatter);

//		registry.addInterceptor(historyInterceptor)
//				.addPathPatterns("/**")
//				.excludePathPatterns(excludeUriPatter);
	}

	/**
	 * Cors 정책
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods(
						HttpMethod.GET.name(),
						HttpMethod.POST.name(),
						HttpMethod.DELETE.name(),
						HttpMethod.PUT.name(),
						HttpMethod.PATCH.name()
				);
	}

}
