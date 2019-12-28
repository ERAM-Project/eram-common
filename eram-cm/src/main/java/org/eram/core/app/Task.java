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

import org.eram.core.annotation.InputChangement;
import org.eram.core.annotation.Offloadable;
import org.eram.core.annotation.Remoteable;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent a task of the application.
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/

@Remoteable(isOffloadable = Offloadable.IS_OFFLOADABLE, isInputChangeable = InputChangement.CHANGE_INPUT)
public abstract class Task<E, T> implements Serializable {

    // Serial ID for serializable.
    private static final long serialVersionUID = 123456789L;

    protected String TAG; // TAG of the task.
    protected List<E> inputs; // Input data of the task.

    /**
     * Constructor to create a task instance.
     * @param taskName   the TAG of the task.
     * @param inputs    The input data.
     */
    public Task(String taskName, E ... inputs) {

        this.TAG = taskName;

        this.inputs = new LinkedList<>();

        for(E e:inputs)
            this.inputs.add(e);
    }

    /**
     * Change the input data of the task.
     * @param inputs    New input data values.
     */
    public void setInputs(List<E> inputs) {

        this.inputs = new LinkedList<>();
        for(E e:inputs)
            this.inputs.add(e);
    }

    /**
     * The job of the task.
     * @return  Output of the task.
     */
    public abstract T run();


    /**
     * @see #toString()
     * @return string.
     */
    @Override
    public String toString() {
        return this.TAG;
    }
}
