package com.taomei.web.share.configuration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.websocket.server.ServerEndpointConfig;

/**
 * 自定义spring配置器
 */
public class CustomSpringConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {
    /**
     * Spring application context.
     * SpringConfigurator uses ContextLoader to obtain spring context.
     * Spring Boot does set up the ServletContext but it never uses ContextLoaderListener
     * which initializes ContextLoader to hold static state of spring context.
     * You can try to add ContextLoaderListener or as a workaround you can write
     * your own context holder and configurator.
     */
    private static volatile BeanFactory context;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CustomSpringConfigurator.context = applicationContext;
    }
}
