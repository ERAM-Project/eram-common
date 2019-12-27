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
import org.eram.core.app.ERAMApplication;
import org.eram.core.app.Task;
import org.eram.core.graph.PFS;
import org.eram.core.graph.TaskNoter;

/**
 * Build an application instance.
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/

public class AppBuilder {

    /**
     * Create an return an application instance.
     * @param appType Type of the application that implementes the interface
     * @see Application
     * @return  An application.
     */
    public static Application buildApp(ApplicationType appType){

        switch (appType){
            case eRAM_APPLICATION: // The default implementation of application.
                PFS pfs = new PFS();
                TaskNoter noter = new TaskNoter();

                ERAMApplication app = new ERAMApplication(pfs, noter);
                return app;

            default:
                    return null;
        }

    }
}
