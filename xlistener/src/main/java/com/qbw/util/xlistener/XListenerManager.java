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

    public interface XListener {
        void onXListen(Object o);
    }
}
