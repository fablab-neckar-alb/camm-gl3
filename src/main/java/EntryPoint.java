import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;




/** sudo chmod o+rw /dev/usb/lp0
 * @author justin
 *
 */
public class EntryPoint {
	private static Settings settings;

	public static void main(String[] args) {
		settings = CmdLine.generateSettingsFromArgs(args);
		SVGParser svgP = new SVGParser();
		parseFile(svgP);
		System.out.println(svgP.getRoot().toString());
		String commands = svgP.getRoot().toCAMM(settings.getGlobal_scale());
		System.out.println(commands);
		try {
			PlotterCommunicator comm = new PlotterCommunicator(settings.getPlotterDevice());
			comm.send(commands);
			comm.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void parseFile(SVGParser parser) {
		try{
			BufferedReader br = new BufferedReader(new FileReader(settings.getInfile()));
			String line;
			int i=0;
			while((line = br.readLine()) != null) {
				parser.parse(line + "\n");
				i++;
			}
			System.out.println(i + " lines parsed.");
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + settings.getInfile() + "\" not found. Abort.");
			e.printStackTrace();
			return;
		} catch (IOException e) {
			System.out.println("IOException. Abort.");
			e.printStackTrace();
			return;
		}
	}

}
