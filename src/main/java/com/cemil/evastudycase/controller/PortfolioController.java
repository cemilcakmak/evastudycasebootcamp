package com.cemil.evastudycase.controller;

import com.cemil.evastudycase.model.Portfolio;
import com.cemil.evastudycase.service.PortfolioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinColumn;
import java.util.List;

@RestController
public class PortfolioController {
    private final static Logger logger = LogManager.getLogger(PortfolioController.class);

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/addPortfolio")
    public Portfolio addPortfolio(@RequestBody Portfolio portfolio) throws Exception {
        return portfolioService.savePortfolio(portfolio);
    }

    @PostMapping("/addPortfolios")
    public List<Portfolio> addPortfolios(@RequestBody List<Portfolio> portfolios) {
        return portfolioService.savePortfolios(portfolios);
    }


    @GetMapping("/portfolio/{id}")
    public Portfolio getPortfolioById(@PathVariable long id) {
        return portfolioService.getPortfolioById(id);
    }

    @GetMapping("/portfolios")
    public List<Portfolio> getAllPortfolios() {
        return portfolioService.getPortfolios();
    }

    @DeleteMapping("/deletePortfolio/{id}")
    public void deletePortfolio(@PathVariable long id) {
        portfolioService.deletePortfolioById(id);
    }

    @PostMapping("/registerPortfolio/{id}")
    public Portfolio registerPortfolio(@PathVariable long id) {
        Portfolio portfolio = portfolioService.getPortfolioById(id);
        portfolio.setRegisterIndex(true);
        return portfolioService.savePortfolio(portfolio);
    }
}
