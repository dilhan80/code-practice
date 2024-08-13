package com.practise;

import java.io.Serializable;
import java.util.Date;

public class DateUtil implements Cloneable, Serializable {
    /** volatile variable containing values are visible to other threads only when write complete **/
    private static volatile DateUtil dateUtil;
    private static final long serialVersionId = 1L;
    private DateUtil(){
    }

    public static DateUtil getInstance() {
        if(dateUtil == null) {
            synchronized (DateUtil.class){
                if(dateUtil == null) {
                    dateUtil = new DateUtil();
                }
            }
        }
        return  dateUtil;
    }

    /** avoid cloning of this singleton **/
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clone not supported exception.");
    }

    /** fix serialization problume (when object are desirialize from disk, single instance cant be different.) **/
    public Object readResolve() {
        return dateUtil;
    }
}
