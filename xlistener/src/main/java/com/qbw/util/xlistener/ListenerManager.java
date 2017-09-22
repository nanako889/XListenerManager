package com.qbw.util.xlistener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class ListenerManager<T> {

    private List<T> mListeners = new ArrayList<>();

    public void addListener(T listener) {
        synchronized (this) {
            if (!mListeners.contains(listener)) {
                mListeners.add(listener);
            }
        }
    }

    public void removeListener(T listener) {
        synchronized (this) {
            if (mListeners.contains(listener)) {
                mListeners.remove(listener);
            }
        }
    }

    public List<T> getListeners() {
        return mListeners;
    }
}
