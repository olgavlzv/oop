package ru.olgavlzv.lab8;

import java.io.IOException;

public class Crawler {
    public static void main(String[] args) throws IOException, InterruptedException {
        String httpPage = "http://ddt.ru/";
        int depth = 2;
        int threads = 4;
        URLPool pool = new URLPool(httpPage, depth, threads);
        for (int i = 0; i < threads; i++) {
            CrawlerTask crawler = new CrawlerTask(pool);
            new Thread(crawler).start();
        }
    }
}