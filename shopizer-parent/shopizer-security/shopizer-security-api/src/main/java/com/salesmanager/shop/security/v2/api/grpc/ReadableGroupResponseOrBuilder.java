// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: shopizer-security.proto

package com.salesmanager.shop.security.v2.api.grpc;

public interface ReadableGroupResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:shopizer.security.ReadableGroupResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .shopizer.security.ReadableGroup groups = 1;</code>
   */
  java.util.List<ReadableGroup>
      getGroupsList();
  /**
   * <code>repeated .shopizer.security.ReadableGroup groups = 1;</code>
   */
  ReadableGroup getGroups(int index);
  /**
   * <code>repeated .shopizer.security.ReadableGroup groups = 1;</code>
   */
  int getGroupsCount();
  /**
   * <code>repeated .shopizer.security.ReadableGroup groups = 1;</code>
   */
  java.util.List<? extends ReadableGroupOrBuilder>
      getGroupsOrBuilderList();
  /**
   * <code>repeated .shopizer.security.ReadableGroup groups = 1;</code>
   */
  ReadableGroupOrBuilder getGroupsOrBuilder(
      int index);
}
