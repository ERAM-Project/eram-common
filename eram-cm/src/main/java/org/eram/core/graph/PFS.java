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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Predecessor First Search algorithm.
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/
public class PFS implements Traversal {

    private MutableGraph<Task> app; /// DAG graph of tasks.

    /**
     * Create a new PFS instance.
     */
    public PFS(){
    }

    /**
     * @see Traversal#traverse(MutableGraph)
     * @param app  the DAG graph.
     * @return The tasks' order to visit.
     */
    @Override
    public Set<Task> traverse(MutableGraph<Task> app) {

        this.app = app;
        if(app.nodes().size() > 0) {
            Task rootTask = app.nodes().iterator().next();
            Set<Task> pfs = new LinkedHashSet<>();
            ArrayList<Task> toVisit = new ArrayList<>();
            Task node;
            toVisit.add(rootTask);

            while (toVisit.size() != 0) {
                node = toVisit.get(0);
                if (this.canVisit(node, pfs)) {
                    if (!pfs.contains(node)) {
                        pfs.add(node);
                        toVisit.addAll(app.successors(node));
                    }
                }
                toVisit.remove(node);
            }

            return pfs;
        }
        return null;
    }

    /**
     * Decide if a task can be visited following PFS algorithm.
     * @param node  The current task.
     * @param pfs   The visited task.
     * @return  true if the task <br>node</br> can be visited. False, otherwise.
     */
    private boolean canVisit(Task node, Set<Task> pfs)
    {
        boolean isOk = true;

        Set<Task> preds = app.predecessors(node);

        for(Task pNode : preds)
        {
            if(pfs.contains(pNode) == false) {
                isOk = false;
                break;
            }
        }

        return isOk;
    }
}
