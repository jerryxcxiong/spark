/**
 *
 */
package com.bosscs.spark.commons.extractor.response;

import com.bosscs.spark.commons.extractor.actions.ActionType;

/**
 * Created by Jerry Xiong on 20/12/15.
 */
public class CloseResponse extends Response {

    private static final long serialVersionUID = -2647516898871636731L;

    private boolean data;

    public CloseResponse() {
        super(ActionType.CLOSE);
        this.data = true;
    }

    public boolean getData() {
        return data;
    }
}
