package com.griddynamics.workshop.imdg.runner.research.repartitioning;

import com.tangosol.net.partition.KeyPartitioningStrategy;

import java.io.Serializable;

/**
* @author mmyslyvtsev@griddynamics.com
* @since 10/18/13
*/
public class Key implements Serializable, KeyPartitioningStrategy.PartitionAwareKey {

    private int id;

    public Key() {
    }


    public Key(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getPartitionId() {
        return id;
    }
}
