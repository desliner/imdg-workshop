package com.griddynamics.workshop.imdg.runner.research.repartitioning;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.net.PartitionedService;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/18/13
 */
public class Client extends Storage {

    @Override
    protected void configure() {
        super.configure();
        System.setProperty("tangosol.coherence.distributed.localstorage", "false");
    }

    @Override
    protected void run() {
        /*NamedCache cache = getCache();
        cache.put(new Key(0), "0");
        cache.put(new Key(1), "1");
        cache.put(new Key(2), "2");*/
        PartitionedService service = getService();
        for (int i = 0; i < service.getPartitionCount(); i++) {
            int primary = service.getPartitionOwner(i).getId();
            int backup = service.getBackupOwner(i, 1).getId();
            log.info("Partition {}: Primary {}, Backup {}", i, primary, backup);
        }
    }

    private PartitionedService getService() {
        return (PartitionedService) CacheFactory.getService("DistributedCache");
    }

    protected NamedCache getCache() {
        return CacheFactory.getCache("cache");
    }
}
