package com.salesmanager.shop.commons.configuration;

import com.salesmanager.shop.commons.util.LabelUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.http.MediaType.IMAGE_GIF;
import static org.springframework.http.MediaType.IMAGE_JPEG;
import static org.springframework.http.MediaType.IMAGE_PNG;

@Configuration
@ComponentScan(basePackages = {
        "com.salesmanager.shop.commons"
})
@EnableJpaRepositories(basePackages = {
        "com.salesmanager.shop.commons.repository"
})
@ServletComponentScan(basePackages = {
        "com.salesmanager.shop.commons"
})
@EnableWebSecurity
@EnableCaching
@ImportResource(locations = {
        "classpath:/spring/shopizer-core-config.xml",
        "classpath:/spring/shopizer-core-ehcache.xml",
})
public class ShopApplicationConfiguration implements WebMvcConfigurer {

    protected final Log logger = LogFactory.getLog(getClass());

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyCode() {
        String workingDir = System.getProperty("user.dir");
        logger.info("Current working directory : " + workingDir);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("shop");
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        List<MediaType> supportedMediaTypes = Arrays.asList(IMAGE_JPEG, IMAGE_GIF, IMAGE_PNG, APPLICATION_OCTET_STREAM);

        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter =
                new ByteArrayHttpMessageConverter();
        byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        return byteArrayHttpMessageConverter;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        return slr;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:bundles/shopizer",
                "classpath:bundles/messages",
                "classpath:bundles/shipping",
                "classpath:bundles/payment");

        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LabelUtils messages() {
        return new LabelUtils();
    }

}
