package com.cemil.evastudycase.service;

import com.cemil.evastudycase.model.Share;
import com.cemil.evastudycase.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {

    private final ShareRepository repository;

    @Autowired
    public ShareService(ShareRepository repository) {
        this.repository = repository;
    }

    public Share saveShare(Share share) {
        return repository.save(share);
    }

    public List<Share> saveShares(List<Share> shareList) {
        return repository.saveAll(shareList);
    }

    public Share getShareById(long id) { return repository.findById(id).orElse(null); }

    public List<Share> getShares() {
        return repository.findAll();
    }

    public void deleteShare(Share share) {
        repository.delete(share);
    }

    public void deleteShareById(long id) {
        repository.deleteById(id);
    }

    public Share getShareByReference(String reference) {
        return repository.findByReference(reference);
    }
}
