import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.*;
import java.util.*;


public class xGen
{
	public static void main(String [] args) throws Exception
	{
		while(args.length != 1)
		{
			System.out.println("Usage: java xGen <class name of interface>");
			System.out.println("Please follow the above line and execute again...");
			System.exit(1);
		}
		//String [] ws = args[0].split(".");
		Class<?> nameOfClass = Class.forName(args[0]);

		//int portNumber = Integer.parseInt(args[1]);

		Method [] classMethods = nameOfClass.getMethods();

		StringBuilder stubBuilder = new StringBuilder();
		File myFile = new File("Stub.java");
		myFile.createNewFile();
		FileWriter fw = new FileWriter(myFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		stubBuilder.append("import java.lang.reflect.Method;\n");
		stubBuilder.append("import java.lang.reflect.Modifier;\n");
		stubBuilder.append("import java.util.*;\n");
		stubBuilder.append("import java.io.*;\n");
		stubBuilder.append("import java.net.*;\n");
		stubBuilder.append("\n");
		stubBuilder.append("public class Stub\n");
		stubBuilder.append("{\n");
		stubBuilder.append("	");
						

		for(Method method: classMethods)
		{	

			String fName = method.getName().toString();
			String fReturn = method.getReturnType().toString();
			String fCall = "public " + fReturn + " " + fName + "(String parameters) throws Exception\n";
			
			String zzz = "Class <?> nameOfClass = Class.forName(\"" + args[0] + "\");\n";

			stubBuilder.append("\n\n\n");
			stubBuilder.append(fCall);
			stubBuilder.append("	{		\n");

			stubBuilder.append("		Socket s = new Socket();\n");
			stubBuilder.append("		String host = \"localhost\";\n");
			stubBuilder.append("		PrintWriter s_out = null;\n");
			stubBuilder.append("		BufferedReader s_in = null;\n");
			stubBuilder.append("		\n");


			stubBuilder.append(zzz);
			stubBuilder.append("		String [] arguments = parameters.split(\",\");\n");
			stubBuilder.append("		Method [] classMethods = nameOfClass.getMethods();\n");
			stubBuilder.append("		\n");
			stubBuilder.append("		int yo = 0;\n");
			stubBuilder.append("		for(Method method: classMethods)\n");
			stubBuilder.append("		{\n");
			stubBuilder.append("			String nm = method.getName().toString();\n");

			String temp = "if(nm.equals(\"" + fName + "\"))" +  "\n";
			stubBuilder.append("			");
			stubBuilder.append(temp);
			stubBuilder.append("				break;\n");
			stubBuilder.append("			yo++;\n");
			stubBuilder.append("		}\n");

			stubBuilder.append("		String remoteMessage = \"\";\n");


			stubBuilder.append("		remoteMessage += classMethods[yo].getReturnType() + \"#\";\n");
			

			stubBuilder.append("		remoteMessage += classMethods[yo].getName() + \"#\";\n");
			
			stubBuilder.append("		Class [] parameterType = classMethods[yo].getParameterTypes();\n");
			stubBuilder.append("		remoteMessage += parameterType.length + \"#\";\n");
			stubBuilder.append("\n");
			stubBuilder.append("		int counter = 0;\n");
			stubBuilder.append("		for(Class parameter: parameterType)\n");
			stubBuilder.append("		{\n");
			stubBuilder.append("			remoteMessage += parameter.getName() + \"#\";\n");
			stubBuilder.append("			remoteMessage += arguments[counter]+\"#\";\n");
			stubBuilder.append("			counter++;\n");
			stubBuilder.append("		}\n");
			stubBuilder.append("		\n");
			stubBuilder.append("		try\n");
			stubBuilder.append("		{\n");
			stubBuilder.append("			s.connect(new InetSocketAddress(host, 8888));\n");
			stubBuilder.append("			s_out = new PrintWriter(s.getOutputStream(), true);\n");
			stubBuilder.append("			s_in = new BufferedReader(new InputStreamReader(s.getInputStream()));\n");
			stubBuilder.append("\n");
			stubBuilder.append("		}\n");
			stubBuilder.append("		catch(UnknownHostException e)\n");
			stubBuilder.append("		{\n");
			stubBuilder.append("			System.err.println(\"Can't resolve host : \" + host);\n");
			stubBuilder.append("			System.exit(1);\n");
			stubBuilder.append("		}\n");
			stubBuilder.append("\n");
			stubBuilder.append("		s_out.println(remoteMessage);\n");
			stubBuilder.append("\n");
			stubBuilder.append("		String tx = s_in.readLine();\n");
			stubBuilder.append("		\n");
			stubBuilder.append("		System.out.println(\"Vale received in the client stub: \" + tx);\n");
			stubBuilder.append("		if(classMethods[yo].getReturnType().toString().equals(\"int\"))\n");
			stubBuilder.append("			return (Integer.parseInt(tx));\n");
			stubBuilder.append("		else\n");
			stubBuilder.append("		{\n");
			stubBuilder.append("			System.out.println(\"Bad return type\");\n");
			stubBuilder.append("			System.out.println(\"The value returned is: \" + tx);\n");
			stubBuilder.append("			System.exit(1);\n");
			stubBuilder.append("		}\n");

			stubBuilder.append("		s_in.close();\n");
			stubBuilder.append("		s_out.close();\n");
			stubBuilder.append("		s.close();\n");

			stubBuilder.append("		return 1;\n");
			stubBuilder.append("	}\n");
		}
		stubBuilder.append("}\n");
		bw.write(stubBuilder.toString());
		bw.close();
		
		StringBuilder skeletonBuilder = new StringBuilder();
		File mySkeleton = new File("Skeleton.java");
		mySkeleton.createNewFile();
		FileWriter fw2 = new FileWriter(mySkeleton.getAbsoluteFile());
		BufferedWriter bw2 = new BufferedWriter(fw2);
		
		skeletonBuilder.append("import java.io.BufferedReader;\n");
		skeletonBuilder.append("import java.util.ArrayList;\n");
		skeletonBuilder.append("import java.lang.reflect.Method;\n");
		skeletonBuilder.append("import java.io.IOException;\n");
		skeletonBuilder.append("import java.io.InputStreamReader;\n");
		skeletonBuilder.append("import java.io.PrintStream;\n");
		skeletonBuilder.append("import java.net.ServerSocket;\n");
		skeletonBuilder.append("import java.net.Socket;\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("public class Skeleton\n");
		skeletonBuilder.append("{\n");
		skeletonBuilder.append("	public static void main(String[] args) throws Exception\n");
		skeletonBuilder.append("	{\n");
		
		skeletonBuilder.append("		try\n");
		skeletonBuilder.append("		{\n");
		
		skeletonBuilder.append("			while(true)\n");
		skeletonBuilder.append("			{\n");

		skeletonBuilder.append("				ServerSocket serverSocket = null;\n");
		skeletonBuilder.append("				Socket sockFromClient = null;\n");
		skeletonBuilder.append("				PrintStream out = null;\n");
		skeletonBuilder.append("				BufferedReader in = null;\n");
		skeletonBuilder.append("				\n");
		skeletonBuilder.append("				String className = \"remoteFunction\";\n");
		skeletonBuilder.append("				serverSocket = new ServerSocket(8888);\n");
		skeletonBuilder.append("				sockFromClient = serverSocket.accept();\n");
		skeletonBuilder.append("				out = new PrintStream(sockFromClient.getOutputStream());\n");
		skeletonBuilder.append("				out.flush();\n");
		skeletonBuilder.append("				in = new BufferedReader(new InputStreamReader(sockFromClient.getInputStream()));\n");
		skeletonBuilder.append("				\n");
		skeletonBuilder.append("				String messageFromClient = in.readLine();\n");
		skeletonBuilder.append("				String [] messageArray = messageFromClient.split(\"#\");\n");
		skeletonBuilder.append("				\n");
		skeletonBuilder.append("				Class<?> nameOfClass = Class.forName(className);\n");
		skeletonBuilder.append("				Object t = nameOfClass.newInstance();\n");
		skeletonBuilder.append("				Method[] allMethods = nameOfClass.getMethods();\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("				for(Method m : allMethods)\n");
		skeletonBuilder.append("				{\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("					String methodName = m.getName().toString();\n");
		skeletonBuilder.append("					Class [] parameterType = m.getParameterTypes();\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("					if(methodName.equals(messageArray[1]))\n");
		skeletonBuilder.append("					{	\n");
		skeletonBuilder.append("						boolean flag = false;\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("						int d = 3;\n");
		skeletonBuilder.append("						for(Class parameter: parameterType)\n");
		skeletonBuilder.append("						{\n");
		skeletonBuilder.append("							String x = parameter.getName().toString();\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("							if(!x.equals(messageArray[d]))\n");
		skeletonBuilder.append("								flag = true;\n");
		skeletonBuilder.append("							d = d + 2;\n");
		skeletonBuilder.append("						}\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("						if(flag == true)\n");
		skeletonBuilder.append("						{\n");
		skeletonBuilder.append("							System.out.println(\"Parameter Mismatch...\");\n");
		skeletonBuilder.append("							System.exit(1);\n");
		skeletonBuilder.append("						}\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("						ArrayList<Object> argArray=new ArrayList<Object>();\n");
		skeletonBuilder.append("						\n");
		skeletonBuilder.append("						for(int i = 3; i < messageArray.length; i++)\n");
		skeletonBuilder.append("						{	\n");
		skeletonBuilder.append("							if(messageArray[i].equals(\"boolean\"))\n");
		skeletonBuilder.append("								argArray.add(Boolean.parseBoolean(messageArray[++i]));\n");
		skeletonBuilder.append("							else if(messageArray[i].equals(\"float\"))\n");
		skeletonBuilder.append("								argArray.add(Float.parseFloat(messageArray[++i]));\n");
		skeletonBuilder.append("							else if(messageArray[i].equals(\"double\"))\n");
		skeletonBuilder.append("								argArray.add(Double.parseDouble(messageArray[++i]));\n");
		skeletonBuilder.append("							else if(messageArray[i].equals(\"int\"))\n");
		skeletonBuilder.append("								argArray.add(Integer.parseInt(messageArray[++i]));\n");
		skeletonBuilder.append("							else\n");
		skeletonBuilder.append("								argArray.add(messageArray[++i]);\n");
		skeletonBuilder.append("							\n");
		skeletonBuilder.append("						}\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("						Object obj = m.invoke(t, argArray.toArray());\n");
		skeletonBuilder.append("						System.out.println(\"Value returned from server: \" + obj.toString());\n");
		skeletonBuilder.append("						out.println(obj.toString());\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("						out.flush();\n");
		skeletonBuilder.append("					}\n");
		skeletonBuilder.append("				}\n");

		skeletonBuilder.append("				in.close();\n");
		skeletonBuilder.append("				out.close();\n");
		skeletonBuilder.append("				serverSocket.close();\n");
		skeletonBuilder.append("			}\n");
		skeletonBuilder.append("		}\n");
		skeletonBuilder.append("		catch(IOException e)\n");
		skeletonBuilder.append("		{\n");
		skeletonBuilder.append("			System.err.println(\"IOException\");\n");
		skeletonBuilder.append("		}\n");
		skeletonBuilder.append("		\n");
		

		skeletonBuilder.append("			\n");
		skeletonBuilder.append("	}\n");
		skeletonBuilder.append("\n");
		skeletonBuilder.append("}\n");
		
		bw2.write(skeletonBuilder.toString());
		bw2.close();

	}
}	
