package com.salesmanager.shop.user.facade;


import com.salesmanager.shop.commons.entity.merchant.MerchantStore;
import com.salesmanager.shop.commons.entity.reference.language.Language;
import com.salesmanager.shop.commons.model.entity.Criteria;
import com.salesmanager.shop.user.entity.UserCriteria;
import com.salesmanager.shop.user.model.PersistableUser;
import com.salesmanager.shop.user.model.ReadablePermission;
import com.salesmanager.shop.user.model.ReadableUser;
import com.salesmanager.shop.user.model.ReadableUserList;
import com.salesmanager.shop.user.model.UserPassword;

import java.util.List;

/**
 * Access to all methods for managing users
 *
 * @author carlsamson
 */
public interface UserFacade {

    /**
     * Finds a User by userName
     *
     * @return
     * @throws Exception
     */
    ReadableUser findByUserName(String userName, String storeCode, Language lang);

    /**
     * Find user by userName
     *
     * @param userName
     * @return
     */
    ReadableUser findByUserName(String userName);

    /**
     * Find user by id
     *
     * @param id
     * @param store
     * @param lang
     * @return
     */
    ReadableUser findById(Long id, MerchantStore store, Language lang);

    /**
     * Creates a User
     *
     * @param user
     * @param store
     */
    ReadableUser create(PersistableUser user, MerchantStore store);

    /**
     * List permissions by group
     *
     * @param ids
     * @return
     * @throws Exception
     */
    List<ReadablePermission> findPermissionsByGroups(List<Integer> ids);

    /**
     * Determines if a user is authorized to perform an action on a specific store
     *
     * @param userName
     * @param merchantStoreCode
     * @return
     * @throws Exception
     */
    boolean authorizedStore(String userName, String merchantStoreCode);


    /**
     * Method to be used in argument resolver.
     *
     * @param store
     * @return
     */
    boolean authorizeStore(MerchantStore store, String path);

    /**
     * Determines if a user is in a specific group
     *
     * @param userName
     * @param groupNames
     */
    void authorizedGroup(String userName, List<String> groupNames);

    /**
     * Check if user is in specific list of roles
     *
     * @param userName
     * @param groupNames
     * @return
     */
    boolean userInRoles(String userName, List<String> groupNames);


    /**
     * Sends reset password email
     *
     * @param user
     * @param store
     * @param language
     */
    void sendResetPasswordEmail(ReadableUser user, MerchantStore store, Language language);

    /**
     * Retrieve authenticated user
     *
     * @return
     */
    String authenticatedUser();

    /**
     * Get by criteria
     *
     * @param criteria
     * @return
     */
    @Deprecated
    ReadableUserList getByCriteria(Language language, String draw, Criteria criteria);

    /**
     * List users
     *
     * @param criteria
     * @param page
     * @param count
     * @param language
     * @return
     */
    ReadableUserList listByCriteria(UserCriteria criteria, int page, int count, Language language);

    /**
     * Delete user
     *
     * @param id
     */
    void delete(Long id, String storeCode);

    /**
     * Update User
     *
     * @param user
     */
    ReadableUser update(Long id, String authenticatedUser, MerchantStore store, PersistableUser user);

    /**
     * Change password request
     *
     * @param userId
     * @param authenticatedUser
     * @param changePassword
     */
    void changePassword(Long userId, String authenticatedUser, UserPassword changePassword);

    void authorizedGroups(String authenticatedUser, PersistableUser user);

    /**
     * Update user enable / disabled flag
     *
     * @param store
     * @param user
     */
    void updateEnabled(MerchantStore store, PersistableUser user);

    /**
     * Forgot password functionality
     *
     * @param userName
     * @param store
     * @param language
     */
    void requestPasswordReset(String userName, String userContextPath, MerchantStore store, Language language);

    /**
     * Validates if a password request is valid
     *
     * @param token
     * @param store
     */
    void verifyPasswordRequestToken(String token, String store);


    /**
     * Reset password
     *
     * @param password
     * @param token
     * @param store
     */
    void resetPassword(String password, String token, String store);

}
