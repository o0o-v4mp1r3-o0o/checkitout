package main;

import java.io.*;

public class Command {
    private static OutputStream os;
    private static OutputStreamWriter osw;
    private static Process process;
    public static String atago;
    public static void start() {
        String projectPath = System.getProperty("user.home");
        String projectPathToTheDriver = "\\IdeaProjects\\81Dojo\\Resources\\Kristallweizen\\Kristallweizen-200214-avx2.exe";
        String projectPathToTheDirectory = "\\IdeaProjects\\81Dojo\\Resources\\Kristallweizen\\";
        try {
            ProcessBuilder pb = new ProcessBuilder(projectPath + projectPathToTheDriver);
            pb.directory(new File(projectPath + projectPathToTheDirectory));
            process = pb.start();
            os = process.getOutputStream();
            osw = new OutputStreamWriter(os);
        } catch (Exception e) {
            // error handling
        } catch (Error e) {
            // error handling
        }
    }

    public static void terminate() throws Exception {
        process.waitFor();
    }

    public static String command(String str) throws Exception {
        String cmd = str + System.lineSeparator();
        osw.write(cmd, 0, cmd.length());
        osw.flush();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = reader.readLine();
        while(reader.ready()) {
            if(line.isEmpty()){
                break;
            }
            System.out.println(line);
            line = reader.readLine();
        }
        System.out.println(line);
        atago = line;
        System.out.println(atago);
        return atago;
    }
}
