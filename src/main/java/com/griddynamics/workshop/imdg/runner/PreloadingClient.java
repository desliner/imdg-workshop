package com.griddynamics.workshop.imdg.runner;

import com.griddynamics.workshop.imdg.preloader.PreloadManager;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/1/13
 */
public class PreloadingClient extends Client {

    @Override
    protected void run() {
        new PreloadManager().preloadAll();
    }

    public static void main(String[] args) {
        new PreloadingClient().main();
    }
}
