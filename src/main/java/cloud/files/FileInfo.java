package cloud.files;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
public class FileInfo implements Serializable {
    String name;
    long length;
    String pathOnClient;
    String pathOnServer;
    int chunkNumber;
    long countOfChunks;
    int chunkSize;
    int lastChunkSize;
    long progress;

    public FileInfo(String name, long length, String path) {
        this.name = name;
        this.length = length;
        this.pathOnClient = path;
        this.pathOnServer = null;
    }
    public FileInfo(File file){
         this(file, 1024);
    }
    public FileInfo(File file, int chunkSize){
        if(file.isFile() && file.exists())
            this.name = file.getName();
            this.length = file.length();
            pathOnClient = file.getPath();
            this.pathOnServer = null;
            this.chunkSize = chunkSize;
            chunkNumber = 0;
            lastChunkSize = (int)length%chunkSize;
            countOfChunks = length/chunkSize;
            if(lastChunkSize > 0)
                countOfChunks++;
        }


    public FileInfo(Path file) throws IOException {
        if(Files.exists(file) && !Files.isDirectory(file)){
            this.name = file.getFileName().toString();
            this.length = Files.size(file);
            this.pathOnClient = file.toAbsolutePath().toString();
            this.pathOnServer = null;
        }
    }

    public void increaseProgress(int chunkLength){
        progress+=chunkLength;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", path='" + pathOnClient + '\'' +
                '}';
    }

}
