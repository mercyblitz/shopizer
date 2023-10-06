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
package com.acme.eureka.tomcat.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_SERVICE_UNAVAILABLE;

/**
 * TODO Comment
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 */
@WebServlet(
        asyncSupported = true, // 激活异步特性
        name = "asyncServlet", // Servlet 名字
        urlPatterns = "/async-servlet"
)
public class AsyncServlet extends HttpServlet {

    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.servletContext = config.getServletContext();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // 判断是否支持异步
        if (request.isAsyncSupported()) {

            // 创建 AsyncContext
            AsyncContext asyncContext = request.startAsync(request, response);
            // 设置超时时间
            asyncContext.setTimeout(50 * 1000L);
            asyncContext.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent event) throws IOException {
                    println("执行完成");
                }

                @Override
                public void onTimeout(AsyncEvent event) throws IOException {
                    HttpServletResponse servletResponse = (HttpServletResponse) event.getSuppliedResponse();
                    servletResponse.setStatus(SC_SERVICE_UNAVAILABLE);
                    println("执行超时");
                }

                @Override
                public void onError(AsyncEvent event) throws IOException {
                    println("执行错误");
                }

                @Override
                public void onStartAsync(AsyncEvent event) throws IOException {
                    println("开始执行");
                }
            });
            println("Hello,World");
            asyncContext.start(() -> {
                println("AsyncContext.start()");
            });
            asyncContext.complete();
        }
    }

    private void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.err.println("AsyncServlet[" + threadName + "]: " + object);
    }
}
