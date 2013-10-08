package com.griddynamics.workshop.imdg.domain.common.model;

import java.io.Serializable;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/1/13
 */
public interface Entity<K> extends Serializable {
    K getId();
}
