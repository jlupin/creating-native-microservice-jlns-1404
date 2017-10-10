package com.example.application;

import com.example.application.base.BaseTest;
import com.example.application.service.interfaces.WelcomeService;
import com.jlupin.impl.client.proxy.remote.producer.ext.JLupinRemoteProxyObjectSupportsExceptionProducerImpl;
import com.jlupin.interfaces.client.delegator.exception.JLupinDelegatorException;
import com.jlupin.interfaces.client.proxy.producer.JLupinProxyObjectProducer;
import com.jlupin.interfaces.common.to.JLupinInputParameter;
import com.jlupin.interfaces.common.to.JLupinOutputParameter;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CurrencyConverterTest extends BaseTest {
    private JLupinProxyObjectProducer getJLupinProxyObjectProducer() {
        return new JLupinRemoteProxyObjectSupportsExceptionProducerImpl(
                "welcome-microservice", getJLupinDelegator(), getJLupinLogger()
        );
    }


    @Test
    public void exampleTest() {
        WelcomeService service = getJLupinProxyObjectProducer().produceObject(WelcomeService.class);
        assertEquals(Collections.singletonMap("message", "Hello Piotr!"), service.getWelcomeMessage(Collections.singletonMap("name", "Piotr")));
    }

    @Test
    public void exampleTestQueue() {
        final JLupinInputParameter jLupinInputParameter = new JLupinInputParameter();
        jLupinInputParameter.setApplicationName("welcome-microservice");
        jLupinInputParameter.setServiceName("welcomeService");
        jLupinInputParameter.setMethodName("logName");
        jLupinInputParameter.setAsynchronousBusName("simpleBus");
        jLupinInputParameter.setParamArray(new Object[] { "Piotr" });

        final JLupinOutputParameter jLupinOutputParameter;
        try { 
            jLupinOutputParameter = getJLupinQueueDelegator().delegate(jLupinInputParameter);
            final String taskId = (String) jLupinOutputParameter.getResultObject();
            assertNotNull(taskId);
        } catch (JLupinDelegatorException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}