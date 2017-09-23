package com.qbw.util.xlistener;

import java.util.ArrayList;

/**
 * Vector本身是线程间同步的，但是只是针对于其自身函数，而我们自己的逻辑还是要做同步处理
 * <p>
 * 所以采用List+同步处理，而不采用Vector
 */
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
            for (XListener listener : mListeners) {
                listener.onXListen(o);
            }
        }
    }

    public void notify2(Object type, Object o) {
        synchronized (mListeners) {
            for (XListener listener : mListeners) {
                if (listener instanceof XListener2) {
                    ((XListener2) listener).onXListen2(type, o);
                }
            }
        }
    }

    public interface XListener {
        void onXListen(Object o);
    }

    public interface XListener2 extends XListener {
        void onXListen2(Object type, Object o);
    }
}
