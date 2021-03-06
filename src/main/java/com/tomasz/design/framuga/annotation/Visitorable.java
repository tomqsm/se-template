package com.tomasz.design.framuga.annotation;

/**
 * Exposes API for callbacks in dbf update lifecycle moments.
 *
 * @author kusmierc
 */
public interface Visitorable {

    /**
     * Runs on a field update in feature.
     * @param updateModel
     */
    void changeDataModel(UpdateModel updateModel);
}
