package com.salesmanager.shop.commons.configuration;

import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.facade.StoreFacade;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

import static com.salesmanager.shop.commons.constants.Constants.DEFAULT_STORE;

@Component
public class MerchantStoreArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantStoreArgumentResolver.class);
    public static final String REQUEST_PARAMATER_STORE = "store";

    @Autowired
    private StoreFacade storeFacade;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(MerchantStore.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String storeValue = Optional.ofNullable(webRequest.getParameter(REQUEST_PARAMATER_STORE))
                .filter(StringUtils::isNotBlank).orElse(DEFAULT_STORE);
        // todo get from cache
        MerchantStore storeModel = storeFacade.get(storeValue);

        // HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        // TODO Move to an api filter
        // authorize request
        // boolean authorized = userFacade.authorizeStore(storeModel, httpServletRequest.getRequestURI());
        // LOGGER.debug("is request authorized {} for {} and store {}", authorized, httpServletRequest.getRequestURI(),
        // 		storeModel.getCode());
        // if(!authorized){
        // 	throw new UnauthorizedException("Cannot authorize user for store " + storeModel.getCode());
        // }
        return storeModel;
    }
}
