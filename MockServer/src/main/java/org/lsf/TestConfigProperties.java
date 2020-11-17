package org.lsf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "test.config")
public class TestConfigProperties {
    private String configvar1;
    private int configvar2;

    public String getConfigvar1() {
        return configvar1;
    }
    public void setConfigvar1(String configvar1) {
        this.configvar1 = configvar1;
    }
    public int getConfigvar2() {
        return configvar2;
    }
    public void setConfigvar2(int configvar2) {
        this.configvar2 = configvar2;
    }
}
