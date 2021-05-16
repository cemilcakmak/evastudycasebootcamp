package com.cemil.evastudycase.service;

import com.cemil.evastudycase.model.Portfolio;
import com.cemil.evastudycase.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository repository;

    @Autowired
    public PortfolioService(PortfolioRepository repository) {
        this.repository = repository;
    }

    public Portfolio savePortfolio(Portfolio portfolio) {
        return repository.save(portfolio);
    }

    public List<Portfolio> savePortfolios(List<Portfolio> portfolioList) {
        return repository.saveAll(portfolioList);
    }

    public Portfolio getPortfolioById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Portfolio getPortfolioByUserId(long userId) { return repository.findByUserId(userId); }

    public List<Portfolio> getPortfolios() {
        return repository.findAll();
    }

    public void deletePortfolio(Portfolio portfolio) {
        repository.delete(portfolio);
    }

    public void deletePortfolioById(long id) { repository.deleteById(id); }

}
