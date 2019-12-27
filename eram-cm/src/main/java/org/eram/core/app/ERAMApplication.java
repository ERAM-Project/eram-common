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

package org.eram.core.app;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import org.eram.core.annotation.InputChangement;
import org.eram.core.annotation.Offloadable;
import org.eram.core.annotation.Remoteable;
import org.eram.core.graph.Noter;
import org.eram.core.graph.Traversal;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/**
 * Represent an application, which is composed of many tasks
 * @see org.eram.core.app.Task
 * @version 1.0
 * @author Houssemeddine MAZOUZI
 **/

public class ERAMApplication implements Application{

    // The tasks' graph of the application.
    private MutableGraph<Task> app;

    // The traversor to visit each task in the application.
    private Traversal traversor;

    // A noter determines the tasks that can be performed independently, in parallel way.
    private Noter taskNote;

    /**
     * Constructor to create an instance of the application.
     * @param traversor The travarsor.
     * @param taskNote The task noter.
     */
    public ERAMApplication(Traversal traversor, Noter taskNote){
        app = GraphBuilder.directed().build();
        this.traversor = traversor;
        this.taskNote = taskNote;

    }

    /**
     * @see Application#constructApp(Task[][], Task[])
     * @param dependencies  the dependencies between the tasks of the application.
     * @param tasks Set of the tasks of the application.
     */
    @Override
    public void constructApp(Task[][] dependencies, Task... tasks) {

        for(Task t:tasks)
            app.addNode(t);
        for(int i=0; i<dependencies.length;i++){
            app.putEdge(dependencies[i][0], dependencies[i][1]);
        }
    }

    /**
     * @see Application#getTasks()
     * @return  Set of the application's tasks.
     */
    @Override
    public Set<Task> getTasks() {
        return this.traversor.traverse(app);
    }

    /**
     * @see Application#taskNote()
     * @return  List of the layer of the application.
     */
    @Override
    public List<Set<Task>> taskNote() {

        return this.taskNote.note(app, this.traversor.traverse(app));
    }

    /**
     * @see Application#predecessors(Task)
     * @param task  The input task.
     * @return  The predecessor tasks.
     */
    @Override
    public Set<Task> predecessors(Task task) {
        return  app.predecessors(task);
    }

    /**
     * @see Application#canOffload(Task)
     * @param task the given task.
     * @return The offloading capability.
     */
    @Override
    public boolean canOffload(Task task) {
        boolean can = true;

        for(Annotation annotation:task.getClass().getAnnotations()){
            if(annotation instanceof Remoteable){
                if(((Remoteable) annotation).isOffloadable() == Offloadable.IS_NON_OFFLOADABLE) {
                    can = false;
                    break;
                }
            }
        }

        return can;
    }

    /**
     * @see Application#canChangeInput(Task)
     * @param task the given task.
     * @return  The input data changement ability.
     */
    @Override
    public boolean canChangeInput(Task task) {
        boolean can = true;

        for(Annotation annotation:task.getClass().getAnnotations()){
            if(annotation instanceof Remoteable){
                if(((Remoteable) annotation).isInputChangeable() == InputChangement.DO_NOT_CHANGE_INPUT) {
                    can = false;
                    break;
                }
            }
        }

        return can;
    }
}
