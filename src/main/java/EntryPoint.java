import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import svg.Circle;
import svg.Element;
import svg.SVG;
import svg.Text;




/** sudo chmod o+rw /dev/usb/lp0
 * @author justin
 *
 */
public class EntryPoint {
	private static Settings settings;

	public static void main(String[] args) {
		settings = CmdLine.generateSettingsFromArgs(args);
		SVGParser svgP = new SVGParser();
		String commands;
		String[] commandArr = {""};
		if(settings.getAsciiBytes()!=null) {
			commands = (new SVG()).toCAMM(0.0);
			commands += settings.getAsciiBytes();
		} else {
			parseFile(svgP);
			svgP.getRoot().flattenAllTransforms(settings.getGlobal_scale());
			settings.setGlobal_scale(1.0);
			if(settings.isSortForY()) {
				for(Element x : svgP.getRoot().getChildren()) {
					try{
						((SVG) x).sortForY();
					} catch (ClassCastException e) {}
				}
			}
			if(settings.isWeirdTreePlotOptim()) optimizeForWTP(svgP.getRoot().getSubTree());
			System.out.println(svgP.getRoot().toString());
			commands = svgP.getRoot().toCAMM(settings.getGlobal_scale());
			commandArr = commands.split(";");
		}
		System.out.println(commands);
		System.out.println();
		try {
			PlotterCommunicator comm = new PlotterCommunicator(settings.getPlotterDevice());
			for(String x : commandArr) {
				comm.send(x + ";");
			}
			//comm.send(commands);
			
			comm.close();
		} catch (AccessDeniedException e) {
			System.out.println("You must grant permission on the device. Please run\n"
					+ "\tsudo chmod o+rw /dev/usb/lp0\nThen try again.");
		} catch (NoSuchFileException e) {
			System.out.println("No connected plotter found. Abort.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void optimizeForWTP(ArrayList<Element> elems) {
		for(Element x : elems) {
			try{
				if(((Circle) x).getRadius() > settings.getSlowCircleMinRadius())
					((Circle) x).setSlow(true);
				if(((Text) x).getLength() / ((Text) x).getCaption().length() < settings.getTextMinCharSize())
					((Text) x).setLength(0.0);
				
			} catch (ClassCastException e) {}
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
