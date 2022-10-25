import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input extends Filter {

	Pipe pipOut;
	String fileName;

	CircularShifter circularShifter;

	public Input(Object o, Pipe outPipe, String file_name) {
		super((Pipe) o, outPipe);
		this.pipOut = outPipe;
		this.fileName = file_name;
	}

	@Override
	public void start() {
		try {
			File myObj = new File(this.fileName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNext()) {
				String data = myReader.nextLine();
				this.pipOut.write(data);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		this.pipOut.close();
		this.stop();
	}

	@Override
	void filter() {
	}
}
