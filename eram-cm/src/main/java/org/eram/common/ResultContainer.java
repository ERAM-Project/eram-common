package org.eram.common;

import java.io.Serializable;

public class ResultContainer implements Serializable {

    private static final long serialVersionUID = 6189L;

    public Object objState;
    public Object functionResult;
    private Long getObjectDuration;
    public Long pureExecutionDuration;


    public ResultContainer(Object state, Object result, Long getObjectDuration, Long duration) {
        objState = state;
        functionResult = result;
        this.getObjectDuration = getObjectDuration;
        pureExecutionDuration = duration;
    }


    public ResultContainer(Object result, Long getObjectDuration) {
        objState = null;
        functionResult = result;
        this.getObjectDuration = getObjectDuration;
        pureExecutionDuration = null;
    }
}
