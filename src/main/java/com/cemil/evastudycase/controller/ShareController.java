package com.cemil.evastudycase.controller;

import com.cemil.evastudycase.model.Share;
import com.cemil.evastudycase.service.ShareService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShareController {

    private final static Logger logger = LogManager.getLogger(ShareController.class);

    private final ShareService shareService;

    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @PostMapping("/addShare")
    public Share addShare(@RequestBody Share share) throws Exception {
        return shareService.saveShare(share);
    }

    @PostMapping("/addShares")
    public List<Share> addShares(@RequestBody List<Share> shares) {
        return shareService.saveShares(shares);
    }

    @GetMapping("/share/{id}")
    public Share getShareById(@PathVariable long id) {
        return shareService.getShareById(id);
    }

    @GetMapping("/shares")
    public List<Share> getAllShares() {
        return shareService.getShares();
    }

    @DeleteMapping("/deleteShare/{id}")
    public void deleteShare(@PathVariable long id) {
        shareService.deleteShareById(id);
    }

    @PostMapping("/registerShare/{id}")
    public Share registerShare(@PathVariable long id) {
        Share share = shareService.getShareById(id);
        share.setRegisterIndex(true);
        return shareService.saveShare(share);
    }
}
