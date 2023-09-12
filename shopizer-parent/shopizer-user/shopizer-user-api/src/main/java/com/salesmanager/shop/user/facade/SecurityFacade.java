package com.salesmanager.shop.user.facade;


import com.salesmanager.shop.user.model.ReadablePermission;

import java.util.List;

public interface SecurityFacade {
  
  /**
   * Get permissions by group
   * @param groups
   * @return
   */
  List<ReadablePermission> getPermissions(List<String> groups);
  
  /**
   * Validates password format
   * @param password
   * @return
   */
  boolean validateUserPassword(final String password);
  
  /**
   * Encode clear password
   * @param password
   * @return
   */
  String encodePassword(final String password);

  /**
   * Validate if both passwords match
   * @param modelPassword (should be encrypted)
   * @param newPassword (should be clear)
   * @return
   */
  boolean matchPassword(String modelPassword, String newPassword);
  
  /**
   * 
   * @param password
   * @param repeatPassword
   * @return
   */
  boolean matchRawPasswords(String password, String repeatPassword);
}
