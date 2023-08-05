package com.zerobase.stock;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

//@SpringBootApplication
public class StockApplication {

    public static void main(String[] args) {
//        SpringApplication.run(StockApplication.class, args);

        try {
            Connection connect = Jsoup.connect("https://finance.yahoo.com/quote/COKE/history?period1=99100800&period2=1691193600&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true");
            Document document = connect.get();

            Elements eles = document.getElementsByAttributeValue("data-test", "historical-prices");
            Element ele = eles.get(0); // table 전체

            Element tbody = ele.children().get(1);// tobody
            for(Element e : tbody.children()){
                String txt = e.text();
                if(!txt.endsWith("Dividend")){
                    continue;
                }

                String[] splits = txt.split(" ");
                String month = splits[0];
                int day = Integer.valueOf(splits[1].replace(",",""));
                int year = Integer.valueOf(splits[2]);
                String dividend = splits[3];

                System.out.println(year + "/" + month + "/" + day + " -> " + dividend);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

}

/** 추출 데이터 경로
 * 배당금 스크래핑
 *
 * https://finance.yahoo.com/robots.txt
 * https://finance.yahoo.com/quote/COKE/history?period1=99100800&period2=1691193600&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true
 *
 * User-agent: *
 * Sitemap: https://finance.yahoo.com/sitemap_en-us_desktop_index.xml
 * Sitemap: https://finance.yahoo.com/sitemap_en-us_quotes_index.xml
 * Sitemap: https://finance.yahoo.com/sitemaps/finance-sitemap_index_US_en-US.xml.gz
 * Sitemap: https://finance.yahoo.com/sitemaps/finance-sitemap_googlenewsindex_US_en-US.xml.gz
 * Disallow: /r/
 * Disallow: /_finance_doubledown/
 * Disallow: /nel_ms/
 * Disallow: /caas/
 * Disallow: /__rapidworker-1.2.js
 * Disallow: /__blank
 * Disallow: /_td_api
 * Disallow: /_remote
 * Disallow: /xhr
 * Disallow: /rmp
 * Disallow: /pdarla/
 *
 * User-agent:googlebot
 * Disallow: /m/
 * Disallow: /screener/insider/
 * Disallow: /caas/
 * Disallow: /fin_ms/
 *
 * User-agent:googlebot-news
 * Disallow: /m/
 * Disallow: /screener/insider/
 * Disallow: /caas/
 * Disallow: /fin_ms/
 * */