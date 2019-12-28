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

import org.eram.core.annotation.InputChangement;
import org.eram.core.annotation.Offloadable;
import org.eram.core.annotation.Remoteable;
import org.eram.core.app.Application;
import org.eram.core.app.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @see org.eram.core.app.Application class tests.
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/

public class ApplicationTest {

    private Application app;

    @Before
    public void createApp() {
        this.app = AppBuilder.buildApp(ApplicationType.eRAM_APPLICATION);

    }

    @Test
    public  void traverserTest(){
        testTask t1 = new testTask("task-1", new Integer[]{1});
        testTask t2 = new testTask("task-2", new Integer[]{1});
        testTask t3 = new testTask("task-3", new Integer[]{1});
        testTask t4 = new testTask("task-1", new Integer[]{1});
        Task [][] deps;

        app.constructApp(null);

        Assert.assertEquals(null, app.getTasks());

        app.constructApp(null, t1);
        Assert.assertEquals(1, app.getTasks().size());

        deps = new Task[4][2];
        deps[0][0] = t1;deps[0][1] = t2;
        deps[1][0] = t1;deps[1][1] = t3;
        deps[2][0] = t2;deps[2][1] = t4;
        deps[3][0] = t3;deps[3][1] = t4;

        app.constructApp(deps, t1, t2, t3, t4);
        Assert.assertArrayEquals(new Task[] {t1, t2, t3, t4}, app.getTasks().toArray());
    }


    // Test implementation of Task class.
    @Remoteable(isOffloadable = Offloadable.IS_NON_OFFLOADABLE, isInputChangeable = InputChangement.DO_NOT_CHANGE_INPUT)
    private class testTask extends Task{


        public testTask(String taskName, Object[] inputs) {
            super(taskName, inputs);
        }

        @Override
        public Object run() {
            return 18;
        }
    }
}
