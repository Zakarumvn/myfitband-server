package com.myfitband.server.entity.mobile;

public class PulseValues {

    private Integer min;
    private Integer max;

    public PulseValues(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }
}
