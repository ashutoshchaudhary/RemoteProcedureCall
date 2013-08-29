public class functionCall
{
	public static void main(String args[]) throws Exception
	{
		Stub client = new Stub();
		int z = client.sum("9,2,11");
		System.out.println("The sum is: " + z);

		int w = client.multiply("9,2,11");
		System.out.println("The product is: " + w);
	}
}