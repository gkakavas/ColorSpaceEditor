package duth.dip.cse.engine.core;

import duth.dip.cse.engine.domain.ImageDataDTO;
import duth.dip.cse.engine.domain.ImageInfo;

import java.util.Optional;

public class ContextHolder {

    private ImageInfo[] appendableBuffer;
    private int index = -1;

    public ContextHolder(int size) {
        appendableBuffer = new ImageInfo[size];
    }

    public void add(ImageInfo imageInfo) {
        if(index < appendableBuffer.length - 1){
            appendableBuffer[++index] = imageInfo;
            return;
        }
        var appendableBuffer = new ImageInfo[this.appendableBuffer.length];
        appendableBuffer[this.appendableBuffer.length - 1] = imageInfo;
        this.appendableBuffer[0].releaseMat();
        System.arraycopy(this.appendableBuffer, 1, appendableBuffer, 0, appendableBuffer.length - 1);
        this.appendableBuffer = appendableBuffer;
    }

    public Optional<ImageInfo> getPrevious() {
        if(!isEmpty() && hasPrevious()){
            return Optional.of(appendableBuffer[--index]);
        }
        return Optional.empty();
    }

    private boolean hasPrevious(){
        return index >= 1;
    }

    private boolean isEmpty(){
        return index == -1;
    }

    public Optional<ImageInfo> getCurrent(){
        if(!isEmpty()){
            return Optional.of(appendableBuffer[index]);
        }
        return Optional.empty();
    }

    public void clear(){
        if(!isEmpty()){
            while (index>=0){
                appendableBuffer[index].releaseMat();
                index --;
            }
            var size = appendableBuffer.length;
            appendableBuffer = new ImageInfo[size];
            index = -1;
        }
    }
}
