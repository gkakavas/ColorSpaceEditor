package duth.dip.cse.service;

import java.nio.channels.AsynchronousFileChannel;

public class MenuService {

    private final AsynchronousFileChannel fileChannel;

    public MenuService(AsynchronousFileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }
}
