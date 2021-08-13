package tech.thehanifs.threads;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SystemThread {
    public static class InternalThread extends Thread {
        public void run() {
            for (int num = 10; num < num+1; num++) {
                byte[] arrayThread = new byte[num * 9999999];
                new Random().nextBytes(arrayThread);

                String randomCharacterData = new String(arrayThread, StandardCharsets.UTF_8);
                SystemThread.temporary.add(randomCharacterData);
            }
        }
    }
    public static final List<String> temporary = new ArrayList<>();
    public List<InternalThread> threads = new ArrayList<>();
    public boolean stopped = false;

    public void run(){
        for (int num = 20; num < num+1; num++) {
            if (stopped) break;
            InternalThread thread = new InternalThread();
            thread.start();
            this.threads.add(thread);
        }
    }
}
