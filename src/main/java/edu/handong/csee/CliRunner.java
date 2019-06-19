package edu.handong.csee;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;


public class CliRunner {
	
	String long_format;
	String force;
	String all;
	String dlink;
	String sort;
	
	boolean help;
	
	public void run(String[] args) {
		Options options = createOptions();

		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			LsCommand ls = new LsCommand();
			
			String a="ls";
	        String s;
	        try {
	        	s=a+" "+args[0];
	        }catch(Exception e) {
	        	s="ls";
	        }
	        
			ls.implementing(s);
	        
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			long_format = cmd.getOptionValue("l");
			force = cmd.getOptionValue("f");
			all = cmd.getOptionValue("a");
			dlink = cmd.getOptionValue("d");
			sort = cmd.getOptionValue("t");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	private Options createOptions() {
		Options options = new Options();
		options.addOption(Option.builder("l").longOpt("long")
				.desc("long format, displaying Unix file types, permissions, number of hard links, owner, group, size, last-modified date and filename")
				.build());
		
		options.addOption(Option.builder("f").longOpt("force")
				.desc("do not sort. Useful for directories containing large numbers of files.")
				.build());
		
		options.addOption(Option.builder("a").longOpt("all")
				.desc(" lists all files in the given directory, including those whose names start with \".\" (which are hidden files in Unix). By default, these files are excluded from the list.")
				.build());
		
		options.addOption(Option.builder("d").longOpt("dlink")
				.desc("shows information about a symbolic link or directory, rather than about the link's target or listing the contents of a directory.")
				.build());

		options.addOption(Option.builder("t").longOpt("sort")
				.desc("sort the list of files by modification time.")
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		return options;
	}

	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI program";
		String footer ="\nThis is implement 'ls' commend";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}
}