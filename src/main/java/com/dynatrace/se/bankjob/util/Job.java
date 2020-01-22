package com.dynatrace.se.bankjob.util;

public enum Job {
	RISKY_JOB("doSomethingRisky Job"),
	HEAVY_CALCULATION("heavyCalculation Job"),
	CHECK_URL("checkUrl Job");
	
	private String text;
	
	Job(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
    
    /**
     * 
     * @param text
     * @return
     */
    public static Job fromString(String text) {
        for (Job b : Job.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
