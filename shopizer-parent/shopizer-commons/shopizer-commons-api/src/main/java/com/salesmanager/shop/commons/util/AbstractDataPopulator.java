/**
 *
 */
package com.salesmanager.shop.commons.util;


import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ConversionException;

import java.util.Locale;


/**
 * @author Umesh A
 */
public abstract class AbstractDataPopulator<Source, Target> implements DataPopulator<Source, Target> {

    private Locale locale;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public Target populate(Source source, MerchantStore store, Language language) throws ConversionException {
        return populate(source, createTarget(), store, language);
    }

    protected abstract Target createTarget();

}
