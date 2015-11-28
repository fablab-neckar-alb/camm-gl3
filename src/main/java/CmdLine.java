import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;


public class CmdLine {
	public static Settings generateSettingsFromArgs(String[] args) {
		Options options = new Options();
		Settings res = null;
		addCommandLineOptions(options);
		
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse( options, args);
			if(cmd.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "camm-gl3 [OPTION]... [FILE]", options);
				System.exit(0);
			}
			res = generateSettingsFromCmd(cmd);
		} catch (UnrecognizedOptionException e) {
			System.out.println("Wrong command line options usage. "
					+ "Use -h for a list of available options.");
			System.exit(0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}

	private static void addCommandLineOptions(Options options) {
		options.addOption("h", "help", false, "displays this help message");
		options.addOption("s", "scale", true, "global scale");
		options.addOption("b", "bytes", true, "enter ascii bytes directly into plotter without"
				+ " looking into file");
	}

	private static Settings generateSettingsFromCmd(CommandLine cmd) {
		Settings res = new Settings();
        String b = cmd.getOptionValue("b");
        if(b!=null) res.setAsciiBytes(b);
        //Here is assumed, that the first argument without a key is the FILE
        else if (cmd.getArgList().size() >= 1) res.setInfile(cmd.getArgList().get(0));
        
		String s = cmd.getOptionValue("s");
        if(s!=null) res.setGlobal_scale(Double.parseDouble(s));

		return res;
	}
}
