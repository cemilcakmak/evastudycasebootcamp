package com.cemil.evastudycase.controller;

import com.cemil.evastudycase.model.Portfolio;
import com.cemil.evastudycase.model.PortfolioShare;
import com.cemil.evastudycase.model.Share;
import com.cemil.evastudycase.service.PortfolioService;
import com.cemil.evastudycase.service.PortfolioShareService;
import com.cemil.evastudycase.service.ShareService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
public class TradeController {

    private final PortfolioService portfolioService;
    private final PortfolioShareService portfolioShareService;
    private final ShareService shareService;

    public TradeController(PortfolioService portfolioService, PortfolioShareService portfolioShareService, ShareService shareService) {
        this.portfolioService = portfolioService;
        this.portfolioShareService = portfolioShareService;
        this.shareService = shareService;
    }

    @PostMapping("/buy/{portfolioId}/{shareReference}/{quantity}")
    @ResponseBody
    public PortfolioShare buyShare(@PathVariable("portfolioId") long portfolioId,
                                   @PathVariable("shareReference") String shareReference,
                                   @PathVariable("quantity") long quantity) {

        Portfolio portfolio = portfolioService.getPortfolioById(portfolioId);
        Share share = shareService.getShareByReference(shareReference);

        String response = null;

        if (share != null && portfolio != null) {
            PortfolioShare portfolioShare = null;

            if (portfolio.isRegisterIndex()) {
                if (share.isRegisterIndex()) {
                    if (quantity <= share.getAmount()) {
                        double totalPrice = quantity * share.getRate();
                        if (totalPrice <= portfolio.getTotalWallet()) {

                            portfolio.setCurrentWallet(new BigDecimal(portfolio.getCurrentWallet() - totalPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
                            portfolioService.savePortfolio(portfolio);

                            share.setAmount(share.getAmount() - quantity);
                            shareService.saveShare(share);

                            portfolioShare = portfolioShareService.getPortfolioShareByPortfolioIdAndShareReference(portfolioId, shareReference);
                            if (portfolioShare == null) {
                                portfolioShare = new PortfolioShare();
                                portfolioShare.setShareReference(share.getReference());
                                portfolioShare.setAmount(quantity);
                                portfolioShare.setTotalPrice(new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
                                portfolioShare.setPortfolioId(portfolio.getId());
                            } else {
                                portfolioShare.setAmount(portfolioShare.getAmount() + quantity);
                                portfolioShare.setTotalPrice(new BigDecimal(portfolioShare.getTotalPrice() + totalPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
                            }
                            portfolioShare = portfolioShareService.savePortfolioShare(portfolioShare);
                            return portfolioShare;
                        } else {
                            response = "There is no enough fund in portfolio to process this operation.";
                        }
                    } else {
                        response = "There is no enough share amount in the market";
                    }
                }else {
                    response = "Share is not registered";
                }
            } else {
                response = "Portfolio is not registered.";
            }
        } else {
            response = "Parameters - Invalid data";
        }


        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, response);
    }


    @PostMapping("/sell/{portfolioId}/{shareReference}/{quantity}")
    @ResponseBody
    public PortfolioShare sellShare(@PathVariable("portfolioId") long portfolioId,
                                    @PathVariable("shareReference") String shareReference,
                                    @PathVariable("quantity") long quantity) {

        PortfolioShare portfolioShare = portfolioShareService.getPortfolioShareByPortfolioIdAndShareReference(portfolioId, shareReference);
        String response = null;
        if (portfolioShare != null) {
            Portfolio portfolio = portfolioService.getPortfolioById(portfolioId);
            Share share = shareService.getShareByReference(shareReference);

            if(portfolio.isRegisterIndex()) {
                if(share.isRegisterIndex()) {
                    if(quantity <= portfolioShare.getAmount()) {
                        double totalPrice = quantity * share.getRate();

                        portfolioShare.setAmount(portfolioShare.getAmount() - quantity);
                        portfolioShare.setTotalPrice(new BigDecimal(portfolioShare.getTotalPrice() - totalPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
                        portfolioShareService.savePortfolioShare(portfolioShare);

                        portfolio.setCurrentWallet(new BigDecimal(portfolio.getCurrentWallet() + totalPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
                        portfolioService.savePortfolio(portfolio);

                        share.setAmount(share.getAmount() + quantity);
                        shareService.saveShare(share);

                        return portfolioShare;
                    } else {
                        response = "There is no enough share amount in portfolio";
                    }
                } else {
                    response = "Share is not registered";
                }
            } else {
                response = "Portfolio is not registered.";
            }
        } else {
            response = "Parameters - Invalid data";
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, response);
    }

}
