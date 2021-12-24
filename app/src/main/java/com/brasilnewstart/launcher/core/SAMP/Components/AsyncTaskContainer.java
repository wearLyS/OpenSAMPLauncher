package com.brasilnewstart.launcher.core.SAMP.Components;

public class AsyncTaskContainer {
    private final ExtendedAsyncTask AsyncTask;

    public AsyncTaskContainer(ExtendedAsyncTask AsyncTask){
        this.AsyncTask = AsyncTask;
        this.AsyncTask.execute(); // Run task
    }

    public void Cancel(Runnable OnFinish){
        this.AsyncTask.Cancel(OnFinish); // Cancel task
    }
    public DefaultTask GetTask(){
        return this.AsyncTask.GetTask();
    }
}