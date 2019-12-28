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

package org.eram.core.annotation;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;


/**
 * Annotation to determine the configuration of a <br> task </br>
 * @see org.eram.core.app.Task
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/

@Retention(RetentionPolicy.RUNTIME)
public @interface Remoteable {

    /**
     *  Indicate if the task can be offloaded.
     * @return the offloadability of the task.
     * @see org.eram.core.app.Task
     */
    Offloadable isOffloadable() default Offloadable.IS_OFFLOADABLE;

    /**
     * Indicate if the input data of the task can be changed.
     * @return
     * @see org.eram.core.app.Task
     */
    InputChangement isInputChangeable() default InputChangement.CHANGE_INPUT;
}
