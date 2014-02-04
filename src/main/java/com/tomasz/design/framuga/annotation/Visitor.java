package com.tomasz.design.framuga.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kusmierc
 */
public class Visitor implements Visitorable {

    private static final Logger LOG = LoggerFactory.getLogger(Visitor.class);

    @Override
    public void changeDataModel(UpdateModel updateModel) {
        LOG.trace("performing long running operation on " + updateModel.getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            LOG.error("{}", ex.getLocalizedMessage());
        }
        LOG.trace("changing | " + updateModel);
        updateModel.setName(updateModel.getName() + " changed ");
        LOG.trace("changed | " + updateModel.getName());
    }
}
