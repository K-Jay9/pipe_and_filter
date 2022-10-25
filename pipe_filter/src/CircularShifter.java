public class CircularShifter extends Filter {

	Pipe pipIn;
	Pipe pipOut;

	Alphabetizer alphabetizer;

	public CircularShifter(Pipe inPipe, Pipe outPipe) {
		super(inPipe, outPipe);
		this.pipIn = inPipe;
		this.pipOut = outPipe;
	}

	@Override
	public void start() {

		while (this.pipIn.isNotEmptyOrIsNotClosed()) {
			String res = "";
			String[] arr = this.pipIn.read().split(" ");
			int k = arr.length;
			while (k-- > 0) {
				String tmp = arr[0];
				for (int i = 1; i < arr.length; i++) {
					arr[i - 1] = arr[i];
				}
				arr[arr.length - 1] = tmp;
				for (String st : arr) {
					res += st + " ";
				}
				this.pipOut.write(res.trim());
				res = "";
			}
		}
		this.pipIn.close();
		this.pipOut.close();
		this.stop();
	}

	@Override
	void filter() {
		alphabetizer = new Alphabetizer(this.pipIn, this.pipOut);
		alphabetizer.start();
	}
}
