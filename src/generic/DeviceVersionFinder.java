package generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DeviceVersionFinder {
	
    public static String getDeviceVersion() throws IOException,InterruptedException{
        String version = "null";
        
        String cmd = "adb -s emulator-5554 shell getprop | grep ro.build.version.release";
        version = executeAsString(cmd);
//        Runtime run = Runtime.getRuntime();
//        ProcessBuilder pb = new ProcessBuilder(ADB+cmd);
//        Process pr = pb.start();
//        pr.waitFor();
////        run.exec(cmd);
//
//        pr.waitFor();
//
//        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//        while ((version=buf.readLine())!=null) {
//            System.out.println("Version Number: "+version);
//        }
        System.out.println("Version Number: "+version);
        return version;
    }
    
    private static String executeAsString(String command) {
        try {
            Process pr = execute(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null) {
                if (!line.isEmpty()) {
                	sb.append(line.substring(29, 32));
                }
            }
            input.close();
            pr.destroy();
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Execution error while executing command" + command, e);
        }
    }    

    private static Process execute(String command) throws IOException, InterruptedException {
        List<String> commandP = new ArrayList<>();
        String[] com = command.split(" ");
        for (int i = 0; i < com.length; i++) {
            commandP.add(com[i]);
        }
        ProcessBuilder prb = new ProcessBuilder(commandP);
        Process pr = prb.start();
        pr.waitFor(10, TimeUnit.SECONDS);
        return pr;
    }

}
