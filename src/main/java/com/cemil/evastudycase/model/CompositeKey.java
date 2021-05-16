package com.cemil.evastudycase.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompositeKey implements Serializable {

    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "seq_no", nullable = false)
    private int seqNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }
}
