/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

    package com.salesmanager.shop.security.v2.api.grpc;

import org.apache.dubbo.common.stream.StreamObserver;

import java.util.concurrent.CompletableFuture;

public interface SecurityApi {

    String JAVA_SERVICE_NAME = "com.salesmanager.shop.security.v2.api.grpc.SecurityApi";
    String SERVICE_NAME = "shopizer.security.SecurityApi";

    ReadablePermissionResponse listPermissions(GroupNameRequest request);

    default CompletableFuture<ReadablePermissionResponse> listPermissionsAsync(GroupNameRequest request){
        return CompletableFuture.completedFuture(listPermissions(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void listPermissions(GroupNameRequest request, StreamObserver<ReadablePermissionResponse> responseObserver){
        listPermissionsAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }

    ReadablePermissionResponse permissions(EmptyRequest request);

    default CompletableFuture<ReadablePermissionResponse> permissionsAsync(EmptyRequest request){
        return CompletableFuture.completedFuture(permissions(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void permissions(EmptyRequest request, StreamObserver<ReadablePermissionResponse> responseObserver){
        permissionsAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }

    ReadableGroupResponse groups(EmptyRequest request);

    default CompletableFuture<ReadableGroupResponse> groupsAsync(EmptyRequest request){
        return CompletableFuture.completedFuture(groups(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void groups(EmptyRequest request, StreamObserver<ReadableGroupResponse> responseObserver){
        groupsAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }






}
