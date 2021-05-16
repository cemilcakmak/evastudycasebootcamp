package com.cemil.evastudycase.service;

import com.cemil.evastudycase.model.PortfolioShare;
import com.cemil.evastudycase.repository.PortfolioShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioShareService {

    private final PortfolioShareRepository repository;

    @Autowired
    public PortfolioShareService(PortfolioShareRepository repository) {
        this.repository = repository;
    }

    public PortfolioShare savePortfolioShare(PortfolioShare portfolioShare) {
        return repository.save(portfolioShare);
    }

    public List<PortfolioShare> savePortfolioShares(List<PortfolioShare> portfolioShareList) {
        return repository.saveAll(portfolioShareList);
    }

    public PortfolioShare getPortfolioShareById(long id) {
        return repository.findById(id).orElse(null);
    }

    public List<PortfolioShare> gePortfolioShares() {
        return repository.findAll();
    }

    public void deletePortfolioShare(PortfolioShare portfolioShare) {
        repository.delete(portfolioShare);
    }

    public void deletePortfolioSharesById(long id) {
        repository.deleteById(id);
    }

    public PortfolioShare getPortfolioShareByPortfolioIdAndShareReference(long portfolioId, String shareReference) {
        return repository.findByPortfolioIdAndShareReference(portfolioId, shareReference);
    }
}
