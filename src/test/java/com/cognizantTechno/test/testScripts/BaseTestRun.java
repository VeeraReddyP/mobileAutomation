package com.cognizantTechno.test.testScripts;

import com.cognizantTechno.test.driverUtils.TestInitializer;
import org.junit.After;
import org.junit.Before;

public class BaseTestRun extends TestInitializer {

    @Before
    public void setUp() throws Exception {
        TestInitializer testInit = new TestInitializer();
        testInit.beforeClass();

    }

    @After
    public void tearDown() {
        TestInitializer testInit = new TestInitializer();
        testInit.afterClass();
    }
}
