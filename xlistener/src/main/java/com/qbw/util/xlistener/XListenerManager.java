package com.qbw.util.xlistener;

public class XListenerManager extends ListenerManager<XListenerManager.XListener> {

    private static XListenerManager sInst;

    private XListenerManager() {}

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

    public void notify(Object o) {
        for (XListener listener : getListeners()) {
            listener.onXListen(o);
        }
    }

    public void notify2(Object type, Object o) {
        for (XListener listener : getListeners()) {
            if (listener instanceof XListener2) {
                ((XListener2) listener).onXListen2(type, o);
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
