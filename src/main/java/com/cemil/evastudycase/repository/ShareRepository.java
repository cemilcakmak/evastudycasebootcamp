package com.cemil.evastudycase.repository;

import com.cemil.evastudycase.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Long> {
    Share findByReference(String reference);

}
