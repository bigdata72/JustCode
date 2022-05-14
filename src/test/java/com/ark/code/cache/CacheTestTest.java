package com.ark.code.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CacheTestTest {
    @Test
    public void whenCacheMiss_thenValueIsComputed() throws Exception{
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key.toUpperCase();
            }
        };
    
        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.SECONDS)            
            .build(loader);
        
        assertThat(cache.size()).isEqualTo(0);
        assertThat(cache.get("hello")).isEqualTo("HELLO");
        assertThat(cache.size()).isEqualTo(1);
        Thread.sleep(5000);
        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.getIfPresent("hello")).isNull();
    }
    
}