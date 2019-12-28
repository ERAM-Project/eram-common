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
import java.util.List;
import java.util.Set;

/**
 * Implement noter algorithm, which notes the tasks in order to decide which ones can be performed
 * in parallel.
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/
public interface Noter {

    /**
     * Note the tasks of a DAG graph.
     * @param app   the DAG graph.
     * @param tasks tasks' set.
     * @return  List of layer of the graph. The tasks of each layer can be executed in
     * parallel manner.
     */
    List<Set<Task>> note(MutableGraph<Task> app, Set<Task> tasks);
}
