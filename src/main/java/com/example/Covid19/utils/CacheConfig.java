package com.example.Covid19.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.config.MaxSizeConfig.MaxSizePolicy;

@Configuration
public class CacheConfig {
	@Bean
	public Config RecoveryCacheConfig() {
		return new Config().setInstanceName("cache-instance")
				.addMapConfig(new MapConfig().setName("recovery-cache").setTimeToLiveSeconds(3600)
						.setMaxSizeConfig(new MaxSizeConfig(20, MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.LFU));

	}
	
	@Bean
	public Config DeathCacheConfig() {
		return new Config().setInstanceName("cache-instance")
				.addMapConfig(new MapConfig().setName("death-cache").setTimeToLiveSeconds(3600)
						.setMaxSizeConfig(new MaxSizeConfig(20, MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.LFU));

	}
	@Bean
	public Config ConfirmCacheConfig() {
		return new Config().setInstanceName("cache-instance")
				.addMapConfig(new MapConfig().setName("confirm-cache").setTimeToLiveSeconds(3600)
						.setMaxSizeConfig(new MaxSizeConfig(20, MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.LFU));

	}
}
