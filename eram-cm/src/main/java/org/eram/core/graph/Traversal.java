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

package org.eram.core.graph;

import com.google.common.graph.MutableGraph;
import org.eram.core.app.Task;
import java.util.Set;

/**
 * The traversal of a DAG graph.
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/
public interface Traversal {

    /**
     * Method of the traversal.
     * @param app the DAG graph of tasks.
     * @return  The tasks' order to visit.
     */
    Set<Task> traverse(MutableGraph<Task> app);
}
