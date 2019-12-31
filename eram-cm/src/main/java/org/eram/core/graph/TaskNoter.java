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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Implement noter algorithm, which notes the tasks in order to decide which ones can be performed
 * in parallel.
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/
public class TaskNoter implements Noter {

    /**
     * @see Noter#note(MutableGraph, Set)
     * @param app   the DAG graph.
     * @param tasks tasks' set.
     * @return List of layer of the graph. The tasks of each layer can be executed in
     * parallel manner.
     */
    @Override
    public List<Set<Task>> note(MutableGraph<Task> app, Set<Task> tasks) {
        List<Set<Task>> notes = new LinkedList<>();


        Map<Task, Integer> mNotes = new LinkedHashMap<>();
        int noteTask = 0;
        for(Task t: tasks){
            if(app.predecessors(t).size() ==0){
                mNotes.put(t, 1);
            }else{
                noteTask  = 0;
                List<Task> preds = new LinkedList<>();
                preds.addAll( app.predecessors(t));
                Collections.sort(preds);

                for(Task g: preds){
                    noteTask  = noteTask  > mNotes.get(g)+1 ? noteTask : mNotes.get(g)+1;
                }
                mNotes.put(t, noteTask );
            }
        }

        for(Task t: mNotes.keySet()){
            if( mNotes.get(t) >= notes.size()+1){
                notes.add(new LinkedHashSet<Task>());
            }
            notes.get(mNotes.get(t)-1).add(t);
        }

        return notes;
    }
}
