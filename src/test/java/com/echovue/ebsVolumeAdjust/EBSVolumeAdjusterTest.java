package com.echovue.ebsVolumeAdjust;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EBSVolumeAdjusterTest {

    @Test
    public void handleRequest() {
        Context context = mock(Context.class);
        LambdaLogger logger = mock(LambdaLogger.class);
        when(context.getLogger()).thenReturn(logger);
        //EBSVolumeAdjuster ebsVolumeAdjuster = new EBSVolumeAdjuster();
    }
}

