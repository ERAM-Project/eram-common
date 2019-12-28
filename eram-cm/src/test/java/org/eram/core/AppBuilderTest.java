/**
 * Copyright (c) 2019 eRAM-Project Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * https://opensource.org/licenses/MIT
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 **/

package org.eram.core;

import org.eram.core.app.Application;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see AppBuilder class.
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/
public class AppBuilderTest {


    @Test
    public void testAppCreation(){

        Application app = AppBuilder.buildApp(ApplicationType.eRAM_APPLICATION);
        Assert.assertNotEquals(app, null);
    }

    @Test
    public void testNullAppCreation(){

        Application app = AppBuilder.buildApp(ApplicationType.OTHER_APPLICATION);
        Assert.assertEquals(app, null);
    }
}
