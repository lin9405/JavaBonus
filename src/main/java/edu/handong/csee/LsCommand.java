package edu.handong.csee;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.cli.Options;

public class LsCommand {
	public void implementing(String s) {
		Process p;
        try {
            p = Runtime.getRuntime().exec(s);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println(s);
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
        	
        }
	}
}
