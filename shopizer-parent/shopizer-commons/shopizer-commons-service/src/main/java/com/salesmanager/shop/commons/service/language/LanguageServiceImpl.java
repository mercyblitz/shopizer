package com.salesmanager.shop.commons.service.language;

import com.salesmanager.shop.commons.constants.Constants;
import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ServiceException;
import com.salesmanager.shop.commons.repository.language.LanguageRepository;
import com.salesmanager.shop.commons.service.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.shop.commons.util.CacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * https://samerabdelkafi.wordpress.com/2014/05/29/spring-data-jpa/
 *
 * @author c.samson
 */

@Service("languageService")
public class LanguageServiceImpl extends SalesManagerEntityServiceImpl<Integer, Language> implements LanguageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageServiceImpl.class);

    @Inject
    private CacheUtils cache;

    private final LanguageRepository languageRepository;

    @Inject
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        super(languageRepository);
        this.languageRepository = languageRepository;
    }


    @Override
    @Cacheable("languageByCode")
    public Language getByCode(String code) throws ServiceException {
        return languageRepository.findByCode(code);
    }

    @Override
    public Locale toLocale(Language language, MerchantStore store) {

        if (store != null) {

            String countryCode = store.getCountry().getIsoCode();

            return new Locale(language.getCode(), countryCode);

        } else {

            return new Locale(language.getCode());
        }
    }

    @Override
    public Language toLanguage(Locale locale) {
        Language language = null;
        try {
            language = getLanguagesMap().get(locale.getLanguage());
        } catch (Exception e) {
            LOGGER.error("Cannot convert locale " + locale.getLanguage() + " to language");
        }
        if (language == null) {
            language = new Language(Constants.DEFAULT_LANGUAGE);
        }
        return language;

    }

    @Override
    public Map<String, Language> getLanguagesMap() throws ServiceException {

        List<Language> langs = this.getLanguages();
        Map<String, Language> returnMap = new LinkedHashMap<String, Language>();

        for (Language lang : langs) {
            returnMap.put(lang.getCode(), lang);
        }
        return returnMap;

    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Language> getLanguages() throws ServiceException {


        List<Language> langs = null;
        try {

            langs = (List<Language>) cache.getFromCache("LANGUAGES");
            if (langs == null) {
                langs = this.list();


                cache.putInCache(langs, "LANGUAGES");
            }

        } catch (Exception e) {
            LOGGER.error("getCountries()", e);
            throw new ServiceException(e);
        }

        return langs;

    }

    @Override
    public Language defaultLanguage() {
        return toLanguage(Locale.ENGLISH);
    }

}
