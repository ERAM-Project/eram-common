/**
 * Copyright (c) 2019 Houssemeddine
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

/**
 * Enumeration of the chooses that indicate if the input data of a <br>Task</br>
 * @see org.eram.core.app.Task can be changed or not.
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/
public enum InputChangement {

    CHANGE_INPUT, // The input data must be changed.
    DO_NOT_CHANGE_INPUT // The input data must be not changed.
}
