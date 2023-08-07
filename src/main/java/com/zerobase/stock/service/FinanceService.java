package com.zerobase.stock.service;

import com.zerobase.stock.model.constants.CacheKey;
import com.zerobase.stock.persist.CompanyRepository;
import com.zerobase.stock.persist.DividendRepository;
import com.zerobase.stock.persist.entity.CompanyEntity;
import com.zerobase.stock.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    @Cacheable(key = "#companyName", value = CacheKey.KEY_FINANCE)
    public Object getDividendByCompanyName(String companyName) {

        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity companyEntity = companyRepository.findByName(companyName)
                .orElseThrow(() -> new IllegalArgumentException("해당 회사를 찾을수 없습니다."));

        // 2. 조회된 회사 ID 로 배당금 정보 조회
        List<DividendEntity> dividendList = dividendRepository.findAllByCompanyId(companyEntity.getId());

        // 3. 결과 조합 후 반환

        return dividendList;
    }
}
