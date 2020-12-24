package ru.olgavlzv.lab8;

import java.util.LinkedList;

public class URLPool {

    private LinkedList<URLDepthPair> viewedLink = new LinkedList<>();
    private LinkedList<URLDepthPair> findLink = new LinkedList<>();
    private final int setDepth;
    private final int setThreads;
    private int waiting;

    public URLPool(String url, int depth, int threads) {
        findLink.add(new URLDepthPair(url, depth));
        setDepth = depth;
        setThreads = threads;
    }

    public synchronized URLDepthPair get() throws InterruptedException {
        if (isEmpty()) {
            waiting++;
            if (waiting == setThreads) {
                getSites();
                System.exit(0);
            }
            wait();
        }
        return findLink.removeFirst();
    }
    public synchronized void addNotProcessed(URLDepthPair pair) {
        findLink.add(pair);
        if (waiting > 0) {
            waiting--;
            notify();
        }
    }

    private boolean isEmpty() {
        return findLink.size() == 0;
    }

    public void getSites() {
        for (int i = 0; i < viewedLink.size(); i++) {
            System.out.println("Глубина: " + (setDepth - viewedLink.get(i).getDepth()) +
                    "\tСсылка: " +  viewedLink.get(i).getURL());
        }
    }


    public void addProcessed(URLDepthPair pair) {
        viewedLink.add(pair);
    }

    public LinkedList<URLDepthPair> getViewedLink() {
        return viewedLink;
    }

    public LinkedList<URLDepthPair> getFindLink() {
        return findLink;
    }

}