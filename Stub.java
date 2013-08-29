import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.io.*;
import java.net.*;

public class Stub
{
	


public int multiply(String parameters) throws Exception
	{		
		Socket s = new Socket();
		String host = "localhost";
		PrintWriter s_out = null;
		BufferedReader s_in = null;
		
Class <?> nameOfClass = Class.forName("interfaceFile");
		String [] arguments = parameters.split(",");
		Method [] classMethods = nameOfClass.getMethods();
		
		int yo = 0;
		for(Method method: classMethods)
		{
			String nm = method.getName().toString();
			if(nm.equals("multiply"))
				break;
			yo++;
		}
		String remoteMessage = "";
		remoteMessage += classMethods[yo].getReturnType() + "#";
		remoteMessage += classMethods[yo].getName() + "#";
		Class [] parameterType = classMethods[yo].getParameterTypes();
		remoteMessage += parameterType.length + "#";

		int counter = 0;
		for(Class parameter: parameterType)
		{
			remoteMessage += parameter.getName() + "#";
			remoteMessage += arguments[counter]+"#";
			counter++;
		}
		
		try
		{
			s.connect(new InetSocketAddress(host, 8888));
			s_out = new PrintWriter(s.getOutputStream(), true);
			s_in = new BufferedReader(new InputStreamReader(s.getInputStream()));

		}
		catch(UnknownHostException e)
		{
			System.err.println("Can't resolve host : " + host);
			System.exit(1);
		}

		s_out.println(remoteMessage);

		String tx = s_in.readLine();
		
		System.out.println("Vale received in the client stub: " + tx);
		if(classMethods[yo].getReturnType().toString().equals("int"))
			return (Integer.parseInt(tx));
		else
		{
			System.out.println("Bad return type");
			System.out.println("The value returned is: " + tx);
			System.exit(1);
		}
		s_in.close();
		s_out.close();
		s.close();
		return 1;
	}



public int sum(String parameters) throws Exception
	{		
		Socket s = new Socket();
		String host = "localhost";
		PrintWriter s_out = null;
		BufferedReader s_in = null;
		
Class <?> nameOfClass = Class.forName("interfaceFile");
		String [] arguments = parameters.split(",");
		Method [] classMethods = nameOfClass.getMethods();
		
		int yo = 0;
		for(Method method: classMethods)
		{
			String nm = method.getName().toString();
			if(nm.equals("sum"))
				break;
			yo++;
		}
		String remoteMessage = "";
		remoteMessage += classMethods[yo].getReturnType() + "#";
		remoteMessage += classMethods[yo].getName() + "#";
		Class [] parameterType = classMethods[yo].getParameterTypes();
		remoteMessage += parameterType.length + "#";

		int counter = 0;
		for(Class parameter: parameterType)
		{
			remoteMessage += parameter.getName() + "#";
			remoteMessage += arguments[counter]+"#";
			counter++;
		}
		
		try
		{
			s.connect(new InetSocketAddress(host, 8888));
			s_out = new PrintWriter(s.getOutputStream(), true);
			s_in = new BufferedReader(new InputStreamReader(s.getInputStream()));

		}
		catch(UnknownHostException e)
		{
			System.err.println("Can't resolve host : " + host);
			System.exit(1);
		}

		s_out.println(remoteMessage);

		String tx = s_in.readLine();
		
		System.out.println("Vale received in the client stub: " + tx);
		if(classMethods[yo].getReturnType().toString().equals("int"))
			return (Integer.parseInt(tx));
		else
		{
			System.out.println("Bad return type");
			System.out.println("The value returned is: " + tx);
			System.exit(1);
		}
		s_in.close();
		s_out.close();
		s.close();
		return 1;
	}
}
