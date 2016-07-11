/**
 *
 */
package com.bosscs.spark.commons.extractor.actions;

import com.bosscs.spark.commons.config.ExtractorConfig;

/**
 * Created by Jerry Xiong on 20/12/15.
 */
public class ExtractorInstanceAction<T> extends Action {

    private static final long serialVersionUID = -1270097974102584045L;

    private ExtractorConfig<T> config;

    public ExtractorInstanceAction(ExtractorConfig<T> config) {
        super(ActionType.EXTRACTOR_INSTANCE);
    }

    public ExtractorConfig<T> getConfig() {
        return config;
    }
}
