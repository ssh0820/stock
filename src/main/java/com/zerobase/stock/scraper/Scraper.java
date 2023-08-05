package com.zerobase.stock.scraper;

import com.zerobase.stock.model.Company;
import com.zerobase.stock.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);
    ScrapedResult scrap(Company company);
}
