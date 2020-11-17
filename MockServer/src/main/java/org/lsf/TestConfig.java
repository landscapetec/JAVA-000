package org.lsf;

public class TestConfig {
    private String configvar1;
    private int configvar2;

    public TestConfig(String configvar1, int configvar2) {
        this.configvar1 = configvar1;
        this.configvar2 = configvar2;
    }

    public String getConfigVar1() {
        return "get var1:" + configvar1;
    }

    public void setConfigVar1(String configVar1) {
        this.configvar1 = configVar1;
    }

    public int getConfigVar2() {
        return 90 * configvar2;
    }

    public void setConfigVar2(int configVar2) {
        this.configvar2 = configVar2;
    }
}
