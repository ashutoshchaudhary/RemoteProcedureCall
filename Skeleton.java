import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Skeleton
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			while(true)
			{
				ServerSocket serverSocket = null;
				Socket sockFromClient = null;
				PrintStream out = null;
				BufferedReader in = null;
				
				String className = "remoteFunction";
				serverSocket = new ServerSocket(8888);
				sockFromClient = serverSocket.accept();
				out = new PrintStream(sockFromClient.getOutputStream());
				out.flush();
				in = new BufferedReader(new InputStreamReader(sockFromClient.getInputStream()));
				
				String messageFromClient = in.readLine();
				String [] messageArray = messageFromClient.split("#");
				
				Class<?> nameOfClass = Class.forName(className);
				Object t = nameOfClass.newInstance();
				Method[] allMethods = nameOfClass.getMethods();

				for(Method m : allMethods)
				{

					String methodName = m.getName().toString();
					Class [] parameterType = m.getParameterTypes();

					if(methodName.equals(messageArray[1]))
					{	
						boolean flag = false;

						int d = 3;
						for(Class parameter: parameterType)
						{
							String x = parameter.getName().toString();

							if(!x.equals(messageArray[d]))
								flag = true;
							d = d + 2;
						}

						if(flag == true)
						{
							System.out.println("Parameter Mismatch...");
							System.exit(1);
						}


						ArrayList<Object> argArray=new ArrayList<Object>();
						
						for(int i = 3; i < messageArray.length; i++)
						{	
							if(messageArray[i].equals("boolean"))
								argArray.add(Boolean.parseBoolean(messageArray[++i]));
							else if(messageArray[i].equals("float"))
								argArray.add(Float.parseFloat(messageArray[++i]));
							else if(messageArray[i].equals("double"))
								argArray.add(Double.parseDouble(messageArray[++i]));
							else if(messageArray[i].equals("int"))
								argArray.add(Integer.parseInt(messageArray[++i]));
							else
								argArray.add(messageArray[++i]);
							
						}

						Object obj = m.invoke(t, argArray.toArray());
						System.out.println("Value returned from server: " + obj.toString());
						out.println(obj.toString());

						out.flush();
					}
				}
				in.close();
				out.close();
				serverSocket.close();
			}
		}
		catch(IOException e)
		{
			System.err.println("IOException");
		}
		
			
	}

}
