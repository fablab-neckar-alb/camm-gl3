import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;


public class PlotterCommunicator {

	private AsynchronousFileChannel channel;
	private ByteBuffer buffer;
	
	public PlotterCommunicator(String infile) throws IOException {
		Path path = Paths.get(infile);
		channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
		buffer = ByteBuffer.allocate(100);
	}
	
	public void send(String input) {
        ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());
	
			
        CompletionHandler handler = new CompletionHandler() {
          @Override
          public void failed(Throwable e, Object attachment) {

            System.out.println(attachment + " failed with exception:");
            e.printStackTrace();
          }
          @Override
          public void completed(Integer result, Object attachment) { 
			
            System.out.println(attachment + " completed and " + result + " bytes are written.");
          } 
        };
	
        channel.write(buffer, 0, "Write operation ALFA", handler);
	
	}
	
	public String read() {
		String res = "";
		Future<Integer> result = channel.read(buffer, 0); // position = 0
		if(result.isDone()) {
			buffer.flip();
			System.out.print("Buffer contents: ");
			while (buffer.hasRemaining()) {
				res += (char) buffer.get();
			}
		}
		return res;
	}
	
	public void close() throws IOException {
		this.buffer.clear();
		this.channel.close();
	}
}
