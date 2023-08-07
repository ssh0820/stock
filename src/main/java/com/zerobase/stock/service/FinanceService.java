package com.zerobase.stock.service;

import com.zerobase.stock.model.constants.CacheKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {

    @Cacheable(key = "#companyName", value = CacheKey.KEY_FINANCE)
    public Object getDividendByCompanyName(String companyName) {

        // 1. 회사명을 기준으로 회사 정보를 조회

        // 2. 조회된 회사 ID 로 배당금 정보 조회

        // 3. 결과 조합 후 반환

        //throw new NotYetImplementedException();

        return null;
    }
}
