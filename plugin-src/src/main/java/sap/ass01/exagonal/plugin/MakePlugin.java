package sap.ass01.exagonal.plugin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MakePlugin {
    public static void main(String[] args) throws IOException, InterruptedException {
        var jarFileName = "TestEffect.jar";
        var dir = "sap";
        var p = Runtime.getRuntime().exec (new String[]{"jar", "cvf", jarFileName, dir}, new String[]{}, new File("target/classes/sap/ass01/plugin/plugins"));
        p.waitFor(10, TimeUnit.SECONDS);
        var dest = new File("../target/"+jarFileName);
        dest.delete();
        new File("target/classes/"+jarFileName).renameTo(dest);
        System.out.println("done.");
    }
}
