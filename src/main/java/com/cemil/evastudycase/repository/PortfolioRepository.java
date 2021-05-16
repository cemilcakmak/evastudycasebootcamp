package com.cemil.evastudycase.repository;

import com.cemil.evastudycase.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Portfolio findByUserId(long userId);
}


