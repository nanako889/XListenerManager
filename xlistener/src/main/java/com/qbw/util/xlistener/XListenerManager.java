package com.qbw.util.xlistener;

import java.util.ArrayList;

public class XListenerManager {

    private static XListenerManager sInst;

    public static XListenerManager getInstance() {
        if (sInst == null) {
            synchronized (XListenerManager.class) {
                if (sInst == null) {
                    sInst = new XListenerManager();
                }
            }
        }
        return sInst;
    }

    private ArrayList<XListener> mListeners;

    private XListenerManager() {
        mListeners = new ArrayList<>();
    }

    public void addListener(XListener listener) {
        synchronized (mListeners) {
            if (!mListeners.contains(listener)) {
                mListeners.add(listener);
            }
        }
    }

    public void removeListener(XListener listener) {
        synchronized (mListeners) {
            if (mListeners.contains(listener)) {
                mListeners.remove(listener);
            }
        }
    }

    public void notify(Object o) {
        synchronized (mListeners) {
            int s = mListeners.size();
            for (int i = s - 1; i >= 0; i--) {
                if (mListeners.get(i).onXListen(o)) {
                    break;
                }
            }
        }
    }

    public void notify2(Object type, Object o) {
        synchronized (mListeners) {
            XListener listener;
            int s = mListeners.size();
            for (int i = s - 1; i >= 0; i--) {
                listener = mListeners.get(i);
                if (listener instanceof XListener2) {
                    if (((XListener2) listener).onXListen2(type, o)) {
                        break;
                    }
                }
            }
        }
    }

    public interface XListener {
        boolean onXListen(Object o);
    }

    public interface XListener2 extends XListener {
        boolean onXListen2(Object type, Object o);
    }
}
