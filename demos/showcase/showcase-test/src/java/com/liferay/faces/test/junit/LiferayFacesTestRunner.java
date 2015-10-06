package com.liferay.faces.test.junit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class LiferayFacesTestRunner extends BlockJUnit4ClassRunner {

    private boolean skip = false;

    /**
     * Constructor.
     *
     * @param clazz the class.
     * @throws InitializationError when initialization fails.
     */
    public LiferayFacesTestRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}

