/*
 * Copyright (c) 2013 Grid Dynamics International, Inc. All Rights Reserved
 * http://www.griddynamics.com
 *
 * IMDG Workshop is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $Id: $
 * @Project:     IMDG Workshop
 * @Description: Demo application for IMDG based on Oracle Coherence
 */

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