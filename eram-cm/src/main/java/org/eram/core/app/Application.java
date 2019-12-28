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

package org.eram.core.app;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Interface represents an application, which is composed of many tasks
 * @see org.eram.core.app.Task
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/

public interface Application extends Serializable {

    /**
     * Construct an application.
     * @param dependencies  the dependencies between the tasks of the application.
     * @param tasks Set of the tasks of the application.
     */
    void constructApp(Task [][] dependencies, Task ... tasks);

    /**
     * Return the tasks of the application.
     * @return  Set of the application's tasks.
     */
    Set<Task> getTasks();

    /**
     * Get the tasks oby layer. The tasks of the same layer can be performed in parallel.
     * @return List of layers.
     */
    List<Set<Task>> taskNote();

    /**
     * Get the predecessor tasks of an input task.
     * @param task  The input task.
     * @return  List of predecessor tasks.
     */
    Set<Task> predecessors(Task task);

    /**
     *  Read the annotation
     *  @see org.eram.core.annotation.Offloadable of a given task.
     * @param task the given task.
     * @return  true if the task ca be offloaded. False otherwise.
     */
    boolean canOffload(Task task);

    /**
     * Read the annotation
     * @see org.eram.core.annotation.InputChangement of a given task.
     * @param task the given task.
     * @return  true if the task's input data must be changed. False, otherwise.
     */
    boolean canChangeInput(Task task);
}
