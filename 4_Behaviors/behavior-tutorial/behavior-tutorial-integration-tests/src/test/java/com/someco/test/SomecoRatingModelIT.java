package com.someco.test;

import com.someco.model.SomeCoRatingsModel;
import org.alfresco.model.ContentModel;
import org.alfresco.rad.test.AlfrescoTestRunner;
import org.alfresco.service.cmr.repository.*;
import org.alfresco.service.namespace.QName;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.alfresco.service.namespace.QName.createQName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(value = AlfrescoTestRunner.class)
public class SomecoRatingModelIT extends BaseIT {

    @Test
    public void testRateableAspect() {
        final double AVG_RATING = 1.0;
        final int RATING_COUNT = 1;
        final int TOTAL = 1;

        NodeService nodeService = getServiceRegistry().getNodeService();

        Map<QName, Serializable> nodeProperties = new HashMap<>();
        this.nodeRef = createNode(getFilename(), ContentModel.TYPE_CONTENT, nodeProperties);

        QName aspectQName = createQName(SomeCoRatingsModel.NAMESPACE_SOMECO_RATINGS_CONTENT_MODEL, SomeCoRatingsModel.ASPECT_SCR_RATEABLE);

        // set up some aspect-based properties
        Map<QName, Serializable> aspectProps = new HashMap<QName, Serializable>();

        aspectProps.put(PROP_AVG_RATING_QNAME, AVG_RATING);
        aspectProps.put(PROP_TOTAL_QNAME, TOTAL);
        aspectProps.put(PROP_COUNT_QNAME, RATING_COUNT);

        nodeService.addAspect(nodeRef, aspectQName, aspectProps);

        assertEquals(AVG_RATING, nodeService.getProperty(this.nodeRef, PROP_AVG_RATING_QNAME));
        assertEquals(TOTAL, nodeService.getProperty(this.nodeRef, PROP_TOTAL_QNAME));
        assertEquals(RATING_COUNT, nodeService.getProperty(this.nodeRef, PROP_COUNT_QNAME));

        assertTrue("Missing aspect",
                getServiceRegistry().getNodeService().hasAspect(nodeRef, aspectQName));
    }
}