package com.qbw.util.xlistener;

import java.util.Vector;

/**
 * @author qinbaowei
 * @createtime 2017/04/26 09:58
 * @email qbaowei@qq.com
 * @description
 */

public abstract class ListenerManager<T> {

    private Vector<T> mListeners = new Vector<>();

    public void addListener(T listener) {
        if (!mListeners.contains(listener)) {
            mListeners.add(listener);
        }
    }

    public void removeListener(T listener) {
        if (mListeners.contains(listener)) {
            mListeners.remove(listener);
        }
    }

    public Vector<T> getListeners() {
        return mListeners;
    }
}
