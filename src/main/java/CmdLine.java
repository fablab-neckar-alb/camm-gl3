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
		
	}

	private static Settings generateSettingsFromCmd(CommandLine cmd) {
		Settings res = new Settings();
		res.setInfile(cmd.getArgList().get(0)); //Here is assumed, that the first argument without a key is the FILE
		return res;
	}
}
