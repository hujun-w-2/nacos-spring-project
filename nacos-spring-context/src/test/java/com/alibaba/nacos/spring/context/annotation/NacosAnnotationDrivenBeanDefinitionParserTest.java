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
package com.alibaba.nacos.spring.context.annotation;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.spring.beans.factory.annotation.NamingServiceInjectedBeanPostProcessor;
import com.alibaba.nacos.spring.config.NacosNamespaceHandler;
import com.alibaba.nacos.spring.context.properties.NacosConfigurationPropertiesBindingPostProcessor;
import com.alibaba.nacos.spring.factory.NacosServiceFactory;
import com.alibaba.nacos.spring.util.NacosBeanUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

/**
 * {@link NacosAnnotationDrivenBeanDefinitionParser} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see NacosNamespaceHandler
 * @since 0.1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/nacos-context.xml"
})
public class NacosAnnotationDrivenBeanDefinitionParserTest {

    static {
        System.setProperty("nacos.server-addr", "127.0.0.1:8080");
    }

    @Autowired
    @Qualifier(NacosBeanUtils.GLOBAL_NACOS_PROPERTIES_BEAN_NAME)
    private Properties globalProperties;

    @Autowired
    @Qualifier(NacosBeanUtils.NACOS_SERVICE_FACTORY_BEAN_NAME)
    private NacosServiceFactory nacosServiceFactory;

    @Autowired
    @Qualifier(NamingServiceInjectedBeanPostProcessor.BEAN_NAME)
    private NamingServiceInjectedBeanPostProcessor namingServiceInjectedBeanPostProcessor;

    @Autowired
    @Qualifier(NacosConfigurationPropertiesBindingPostProcessor.BEAN_NAME)
    private NacosConfigurationPropertiesBindingPostProcessor nacosConfigurationPropertiesBindingPostProcessor;

    @Autowired
    @Qualifier(NacosConfigListenerMethodProcessor.BEAN_NAME)
    private NacosConfigListenerMethodProcessor nacosConfigListenerMethodProcessor;

    @Autowired
    @Qualifier(NacosPropertySourcePostProcessor.BEAN_NAME)
    private NacosPropertySourcePostProcessor nacosPropertySourcePostProcessor;

    @NacosService
    private ConfigService configService;

    @Test
    public void test() {
        Assert.assertNotNull(globalProperties);
        Assert.assertNotNull(nacosServiceFactory);
        Assert.assertNotNull(namingServiceInjectedBeanPostProcessor);
        Assert.assertNotNull(nacosConfigurationPropertiesBindingPostProcessor);
        Assert.assertNotNull(nacosConfigListenerMethodProcessor);
        Assert.assertNotNull(nacosPropertySourcePostProcessor);
    }

}