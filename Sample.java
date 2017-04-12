public class Sample {
	public static void main(String[] args) {
		try {
			String hello_world = String.format("Hello, %s", args[0]);
			System.out.println(hello_world);
		} catch (Exception e) {
			System.out.println("must pass an argument");
		}
	}
}
