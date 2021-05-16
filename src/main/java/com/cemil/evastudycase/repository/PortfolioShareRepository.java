package com.cemil.evastudycase.repository;

import com.cemil.evastudycase.model.PortfolioShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioShareRepository extends JpaRepository<PortfolioShare, Long> {

    PortfolioShare findByPortfolioIdAndShareReference(long portfolioId, String shareReference);
}
