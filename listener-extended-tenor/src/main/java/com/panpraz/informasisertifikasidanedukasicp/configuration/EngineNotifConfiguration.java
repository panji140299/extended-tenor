package com.panpraz.informasisertifikasidanedukasicp.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "enginenotif")
@Setter
@Getter
public class EngineNotifConfiguration {
    private String url;
    private Integer source;
    private String sourceAppCpr;
    private String sourceAppPrCpr;
    private String sourceAppPrNcpr;
    private String sourceAppExtendedTenor;
}
