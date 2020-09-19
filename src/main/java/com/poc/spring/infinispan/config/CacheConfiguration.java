package com.poc.spring.infinispan.config;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class CacheConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CacheConfiguration.class);

    @Bean
    public EmbeddedCacheManager embeddedCacheManager() throws IOException {
        EmbeddedCacheManager manager = new DefaultCacheManager("infinispan.xml");
        manager.getCache("cacheEmployeeByName").addListener(new CacheListener());
        manager.getCache("cacheEmployeeByDepartmentAndName").addListener(new CacheListener());

        return manager;
    }

    @Bean
    public CacheManager cacheManager(@Autowired EmbeddedCacheManager nativeCacheManager) {
        return new SpringEmbeddedCacheManager(nativeCacheManager);
    }
}