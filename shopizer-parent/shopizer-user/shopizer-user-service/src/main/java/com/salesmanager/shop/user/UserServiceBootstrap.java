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
package com.salesmanager.shop.user;

import io.microsphere.spring.webmvc.annotation.EnableWebMvcExtension;
import io.microsphere.spring.webmvc.handler.ReversedProxyHandlerMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Shopizer User-Service Spring Boot Bootstrap
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableWebMvcExtension
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.salesmanager.shop.user.reposistory")
@EntityScan(basePackages = "com.salesmanager.shop.commons.entity.user")
@EnableTransactionManagement
@SpringBootApplication(
        scanBasePackages = {"com.salesmanager.shop"},
        exclude = {
                ManagementWebSecurityAutoConfiguration.class}
)
@Import(ReversedProxyHandlerMapping.class)
public class UserServiceBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceBootstrap.class, args);
    }

//    @Value("${spring.application.name}")
//    private String applicationName;
//
//    @Autowired
//    private MeterRegistry meterRegistry;
//
//    @PostConstruct
//    public void init() {
//        meterRegistry.config().commonTags("application", applicationName);
//    }
}
