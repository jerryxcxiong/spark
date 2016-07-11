/**
 *
 */
package com.bosscs.spark.commons.extractor.response;

import com.bosscs.spark.commons.config.ExtractorConfig;
import com.bosscs.spark.commons.extractor.actions.ActionType;
import com.bosscs.spark.commons.rdd.IExtractor;

/**
 * Created by Jerry Xiong on 20/12/15.
 */
public class ExtractorInstanceResponse<T> extends Response {

    private static final long serialVersionUID = -2647516898871636731L;

    private IExtractor<T, ExtractorConfig<T>> data;

    public ExtractorInstanceResponse(IExtractor<T, ExtractorConfig<T>> extractor) {
        super(ActionType.CLOSE);
        this.data = extractor;
    }

    public IExtractor<T, ExtractorConfig<T>> getData() {
        return data;
    }
}
