package sap.ass01.exagonal.plugin;


import java.io.File;
import java.util.concurrent.TimeUnit;

public class Main {
        public static void main(String[] args) {
                var jarFileName = "A.jar";
		var dir = "plugin-src";
		var p = Runtime.getRuntime().exec (new String[]{"jar", "cvf", jarFileName, dir}, new String[]{}, new File("target/classes"));		
		p.waitFor(10, TimeUnit.SECONDS);
		var dest = new File("."+jarFileName);
		dest.delete();
		new File("target/classes/"+jarFileName).renameTo(dest);
		System.out.println("done.");
        }
}