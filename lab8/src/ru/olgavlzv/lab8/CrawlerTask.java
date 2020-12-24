package ru.olgavlzv.lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class CrawlerTask implements Runnable {

    final static int anyDepth = 0;
    private final URLPool urlPool;

    @Override
    public void run() {
        try {
            Scan();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CrawlerTask(URLPool pool) {
        urlPool = pool;
    }

    private void Scan() throws IOException, InterruptedException {
        while (true) {
            Process(urlPool.get());
        }
    }

    private void Process(URLDepthPair pair) throws IOException{
        URL url = new URL(pair.getURL());
        URLConnection connection = url.openConnection();

        String redirect = connection.getHeaderField("Location");
        if (redirect != null) {
            connection = new URL(redirect).openConnection();
        }

        urlPool.addProcessed(pair);
        if (pair.getDepth() == 0) return;

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input;
        while ((input = reader.readLine()) != null) {
            String m_Prefix = "http";
            while (input.contains("a href=\"" + m_Prefix)) {
                input = input.substring(input.indexOf("a href=\"" + m_Prefix) + 8);
                String link = input.substring(0, input.indexOf('\"'));
                if (link.contains(" "))
                    link = link.replace(" ", "%20");
                if (urlPool.getFindLink().contains(new URLDepthPair(link, anyDepth)) ||
                        urlPool.getViewedLink().contains(new URLDepthPair(link, anyDepth))) continue;
                urlPool.addNotProcessed(new URLDepthPair(link, pair.getDepth() - 1));
            }
        }
        reader.close();
    }
}