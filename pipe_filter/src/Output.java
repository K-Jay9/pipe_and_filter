import java.io.FileWriter;
import java.io.IOException;

public class Output extends Filter {

	Pipe pipIn;
	String fileName;

	public Output(Pipe inPipe, Object o, String file_name) {
		super(inPipe, (Pipe) o);
		this.pipIn = inPipe;
		this.fileName = file_name;
	}

	@Override
	public void start() {
		try {
			FileWriter myFile = new FileWriter(this.fileName);
			while (this.pipIn.isNotEmptyOrIsNotClosed()) {
				myFile.write(this.pipIn.read());
				myFile.write("\n");
			}
			myFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pipIn.close();
		this.stop();
	}

	@Override
	void filter() {

	}
}
