/**
 * 
 */
package com.salesmanager.shop.commons.util;


import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.exception.ConversionException;

/**
 * @author Umesh A
 *
 */
public interface DataPopulator<Source,Target> {

    Target populate(Source source, Target target, MerchantStore store, Language language) throws ConversionException;
    Target populate(Source source, MerchantStore store, Language language) throws ConversionException;

}
