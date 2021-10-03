package cloud.server.utils;

import cloud.files.FileInfo;
import cloud.server.state.State;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileUtils {
    static ConcurrentHashMap<Integer, FileInfo> currentClientsFiles = new ConcurrentHashMap<>();         //TODO сделать список файлов
    static ConcurrentHashMap<Integer, State> currentStateForClient = new ConcurrentHashMap<>();

    public static FileInfo getFileInfoByClient(int hashclient) {
        //System.out.println("получаю" + hashclient);
        return currentClientsFiles.get(hashclient);
    }

    public static void addCurrentFile(Integer clientHash, FileInfo fileInfo) {
        //System.out.println("добавил" + clientHash);
        currentClientsFiles.put(clientHash, fileInfo);
    }

    public static void changeCurrentStateForClient(int clientHash, State state){
        currentStateForClient.put(clientHash, state);
       // System.out.println("поменял состояние для " + clientHash);
    }
    public static void removeFileInfoByClient(int clientHash){
        currentClientsFiles.remove(clientHash);
    }

    public static State checkStateForClient(int clientHash){
        return currentStateForClient.get(clientHash);
    }
}
