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

import com.google.protobuf.Message;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.PathResolver;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.ServerService;
import org.apache.dubbo.rpc.TriRpcStatus;
import org.apache.dubbo.rpc.model.MethodDescriptor;
import org.apache.dubbo.rpc.model.ServiceDescriptor;
import org.apache.dubbo.rpc.model.StubMethodDescriptor;
import org.apache.dubbo.rpc.model.StubServiceDescriptor;
import org.apache.dubbo.rpc.stub.StubInvocationUtil;
import org.apache.dubbo.rpc.stub.StubInvoker;
import org.apache.dubbo.rpc.stub.StubMethodHandler;
import org.apache.dubbo.rpc.stub.StubSuppliers;
import org.apache.dubbo.rpc.stub.UnaryStubMethodHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public final class DubboSecurityApiTriple {

    public static final String SERVICE_NAME = SecurityApi.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,SecurityApi.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,ServiceDiscoveryOuter.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboSecurityApiTriple::newStub);
        StubSuppliers.addSupplier(SecurityApi.JAVA_SERVICE_NAME,  DubboSecurityApiTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(SecurityApi.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static SecurityApi newStub(Invoker<?> invoker) {
        return new SecurityApiStub((Invoker<SecurityApi>)invoker);
    }

    private static final StubMethodDescriptor listPermissionsMethod = new StubMethodDescriptor("listPermissions",
    GroupNameRequest.class, ReadablePermissionResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), GroupNameRequest::parseFrom,
    ReadablePermissionResponse::parseFrom);

    private static final StubMethodDescriptor listPermissionsAsyncMethod = new StubMethodDescriptor("listPermissions",
    GroupNameRequest.class, CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), GroupNameRequest::parseFrom,
    ReadablePermissionResponse::parseFrom);

    private static final StubMethodDescriptor listPermissionsProxyAsyncMethod = new StubMethodDescriptor("listPermissionsAsync",
    GroupNameRequest.class, ReadablePermissionResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), GroupNameRequest::parseFrom,
    ReadablePermissionResponse::parseFrom);

    private static final StubMethodDescriptor permissionsMethod = new StubMethodDescriptor("permissions",
    EmptyRequest.class, ReadablePermissionResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), EmptyRequest::parseFrom,
    ReadablePermissionResponse::parseFrom);

    private static final StubMethodDescriptor permissionsAsyncMethod = new StubMethodDescriptor("permissions",
    EmptyRequest.class, CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), EmptyRequest::parseFrom,
    ReadablePermissionResponse::parseFrom);

    private static final StubMethodDescriptor permissionsProxyAsyncMethod = new StubMethodDescriptor("permissionsAsync",
    EmptyRequest.class, ReadablePermissionResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), EmptyRequest::parseFrom,
    ReadablePermissionResponse::parseFrom);

    private static final StubMethodDescriptor groupsMethod = new StubMethodDescriptor("groups",
    EmptyRequest.class, ReadableGroupResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), EmptyRequest::parseFrom,
    ReadableGroupResponse::parseFrom);

    private static final StubMethodDescriptor groupsAsyncMethod = new StubMethodDescriptor("groups",
    EmptyRequest.class, CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), EmptyRequest::parseFrom,
    ReadableGroupResponse::parseFrom);

    private static final StubMethodDescriptor groupsProxyAsyncMethod = new StubMethodDescriptor("groupsAsync",
    EmptyRequest.class, ReadableGroupResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), EmptyRequest::parseFrom,
    ReadableGroupResponse::parseFrom);





    public static class SecurityApiStub implements SecurityApi{
        private final Invoker<SecurityApi> invoker;

        public SecurityApiStub(Invoker<SecurityApi> invoker) {
            this.invoker = invoker;
        }

        @Override
        public ReadablePermissionResponse listPermissions(GroupNameRequest request){
            return StubInvocationUtil.unaryCall(invoker, listPermissionsMethod, request);
        }

        public CompletableFuture<ReadablePermissionResponse> listPermissionsAsync(GroupNameRequest request){
            return StubInvocationUtil.unaryCall(invoker, listPermissionsAsyncMethod, request);
        }

        @Override
        public void listPermissions(GroupNameRequest request, StreamObserver<ReadablePermissionResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, listPermissionsMethod , request, responseObserver);
        }
        @Override
        public ReadablePermissionResponse permissions(EmptyRequest request){
            return StubInvocationUtil.unaryCall(invoker, permissionsMethod, request);
        }

        public CompletableFuture<ReadablePermissionResponse> permissionsAsync(EmptyRequest request){
            return StubInvocationUtil.unaryCall(invoker, permissionsAsyncMethod, request);
        }

        @Override
        public void permissions(EmptyRequest request, StreamObserver<ReadablePermissionResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, permissionsMethod , request, responseObserver);
        }
        @Override
        public ReadableGroupResponse groups(EmptyRequest request){
            return StubInvocationUtil.unaryCall(invoker, groupsMethod, request);
        }

        public CompletableFuture<ReadableGroupResponse> groupsAsync(EmptyRequest request){
            return StubInvocationUtil.unaryCall(invoker, groupsAsyncMethod, request);
        }

        @Override
        public void groups(EmptyRequest request, StreamObserver<ReadableGroupResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, groupsMethod , request, responseObserver);
        }



    }

    public static abstract class SecurityApiImplBase implements SecurityApi, ServerService<SecurityApi> {

        private <T, R> BiConsumer<T, StreamObserver<R>> syncToAsync(java.util.function.Function<T, R> syncFun) {
            return new BiConsumer<T, StreamObserver<R>>() {
                @Override
                public void accept(T t, StreamObserver<R> observer) {
                    try {
                        R ret = syncFun.apply(t);
                        observer.onNext(ret);
                        observer.onCompleted();
                    } catch (Throwable e) {
                        observer.onError(e);
                    }
                }
            };
        }

        @Override
        public final Invoker<SecurityApi> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/listPermissions" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/listPermissionsAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/permissions" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/permissionsAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/groups" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/groupsAsync" );

            BiConsumer<GroupNameRequest, StreamObserver<ReadablePermissionResponse>> listPermissionsFunc = this::listPermissions;
            handlers.put(listPermissionsMethod.getMethodName(), new UnaryStubMethodHandler<>(listPermissionsFunc));
            BiConsumer<GroupNameRequest, StreamObserver<ReadablePermissionResponse>> listPermissionsAsyncFunc = syncToAsync(this::listPermissions);
            handlers.put(listPermissionsProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(listPermissionsAsyncFunc));
            BiConsumer<EmptyRequest, StreamObserver<ReadablePermissionResponse>> permissionsFunc = this::permissions;
            handlers.put(permissionsMethod.getMethodName(), new UnaryStubMethodHandler<>(permissionsFunc));
            BiConsumer<EmptyRequest, StreamObserver<ReadablePermissionResponse>> permissionsAsyncFunc = syncToAsync(this::permissions);
            handlers.put(permissionsProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(permissionsAsyncFunc));
            BiConsumer<EmptyRequest, StreamObserver<ReadableGroupResponse>> groupsFunc = this::groups;
            handlers.put(groupsMethod.getMethodName(), new UnaryStubMethodHandler<>(groupsFunc));
            BiConsumer<EmptyRequest, StreamObserver<ReadableGroupResponse>> groupsAsyncFunc = syncToAsync(this::groups);
            handlers.put(groupsProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(groupsAsyncFunc));




            return new StubInvoker<>(this, url, SecurityApi.class, handlers);
        }


        @Override
        public ReadablePermissionResponse listPermissions(GroupNameRequest request){
            throw unimplementedMethodException(listPermissionsMethod);
        }

        @Override
        public ReadablePermissionResponse permissions(EmptyRequest request){
            throw unimplementedMethodException(permissionsMethod);
        }

        @Override
        public ReadableGroupResponse groups(EmptyRequest request){
            throw unimplementedMethodException(groupsMethod);
        }





        @Override
        public final ServiceDescriptor getServiceDescriptor() {
            return serviceDescriptor;
        }
        private RpcException unimplementedMethodException(StubMethodDescriptor methodDescriptor) {
            return TriRpcStatus.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented",
                "/" + serviceDescriptor.getInterfaceName() + "/" + methodDescriptor.getMethodName())).asException();
        }
    }

}
