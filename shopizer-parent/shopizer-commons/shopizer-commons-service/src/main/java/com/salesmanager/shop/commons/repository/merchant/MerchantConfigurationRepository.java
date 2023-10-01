package com.salesmanager.shop.commons.repository.merchant;

import com.salesmanager.shop.commons.entity.system.MerchantConfiguration;
import com.salesmanager.shop.commons.enums.MerchantConfigurationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MerchantConfigurationRepository extends JpaRepository<MerchantConfiguration, Long> {

	//List<MerchantConfiguration> findByModule(String moduleName);
	
	//MerchantConfiguration findByCode(String code);
	
	@Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=?1")
	List<MerchantConfiguration> findByMerchantStore(Integer id);
	
	@Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=?1 and m.key=?2")
	MerchantConfiguration findByMerchantStoreAndKey(Integer id, String key);
	
	@Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=?1 and m.merchantConfigurationType=?2")
	List<MerchantConfiguration> findByMerchantStoreAndType(Integer id, MerchantConfigurationType type);
}
