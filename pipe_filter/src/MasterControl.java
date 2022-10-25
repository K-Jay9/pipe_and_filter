public class MasterControl {

	Pipe pipIn = new Pipe();
	Pipe pipeOut = new Pipe();

	Pipe pipeThree = new Pipe();
	Input input;
	CircularShifter circularShifter;
	Alphabetizer alphabetizer;
	Output output;
	Object o;

	MasterControl() {
	}

	public void start(String inputFileName, String outputFileName) {
		input = new Input(this.o, this.pipIn, inputFileName);
		input.start();

		circularShifter = new CircularShifter(this.pipIn, this.pipeOut);
		circularShifter.start();

		alphabetizer = new Alphabetizer(this.pipeOut, this.pipeOut);
		alphabetizer.start();

		output = new Output(this.pipeOut, this.o, outputFileName);
		output.start();

	}

}
