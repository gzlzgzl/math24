import java.io.*;
import java.util.*;
public class Calculator
{ 
	public Calculator(){}
	//constructing a calculator object that stores computation methods.
	public static int gcd(int x, int y)
	{
		while(x%y!=0)
		{
			int m=x%y;
			x=y;
			y=m;
		}
		return y;
	}
	//finding the greatest common denominator of two integers
	
	public static boolean isnumber(String st)
	{
		return st.indexOf(" ")==-1;
	}
	//determining whether an expression is a single number
	
	public static boolean isinteger(String st)
	{
		if(!isnumber(st))
			return false;
		return st.indexOf("/")==-1;
	}
	//determining whether a number is an integer
	
	public static int getnumerator(String st)
	{
		int in=st.indexOf("/");
		return Integer.parseInt(st.substring(0,in));
	}
	//find the numerator of a fraction
	
	public static int getdenominator(String st)
	{
		int in=st.indexOf("/");
		return Integer.parseInt(st.substring(in+1));
	}
	//find the denominator of a fraction
	
	public static String simplify(String st)
	{
		int n=getnumerator(st);
		int m=getdenominator(st);
		int n1=n/gcd(n,m);
		int m1=m/gcd(n,m);
		if((n1>0)&&(m1<0))
		{
			n1=-n1;m1=-m1;
		}
		if(m1==1)return Integer.toString(n1);
		return Integer.toString(n1)+"/"+Integer.toString(m1);
	}
	//simplify a fraction
	
	
	
	
	public static String sum(String st1, String st2)
	{
		String s1=st1,s2=st2;
		if(isinteger(s1))s1+="/1";
		if(isinteger(s2))s2+="/1";
		int n1=getnumerator(s1),n2=getnumerator(s2);
		int d1=getdenominator(s1),d2=getdenominator(s2);
		int dd=d1*d2;
		int nn=n1*d2+n2*d1;
		String str=Integer.toString(nn)+"/"+Integer.toString(dd);
		str=simplify(str);
		return str;
	}
	//return the sum of two numbers
	
	
	public static String difference(String st1, String st2)
	{
		String s1=st1,s2=st2;
		if(isinteger(s1))s1+="/1";
		if(isinteger(s2))s2+="/1";
		int n1=getnumerator(s1),n2=getnumerator(s2);
		int d1=getdenominator(s1),d2=getdenominator(s2);
		int dd=d1*d2;
		int nn=n1*d2-n2*d1;
		String str=Integer.toString(nn)+"/"+Integer.toString(dd);
		str=simplify(str);
		return str;
	}
	//return the difference of two numbers
	
	
	public static String product(String st1, String st2)
	{
		String s1=st1,s2=st2;
		if(isinteger(s1))s1+="/1";
		if(isinteger(s2))s2+="/1";
		int n1=getnumerator(s1),n2=getnumerator(s2);
		int d1=getdenominator(s1),d2=getdenominator(s2);
		int dd=d1*d2;
		int nn=n1*n2;
		String str=Integer.toString(nn)+"/"+Integer.toString(dd);
		str=simplify(str);
		return str;
	}
	//return the product of two numbers
	
	
	public static String quotient(String st1, String st2)
	{
		String s1=st1,s2=st2;
		if(isinteger(s1))s1+="/1";
		if(isinteger(s2))s2+="/1";
		int n1=getnumerator(s1),n2=getnumerator(s2);
		int d1=getdenominator(s1),d2=getdenominator(s2);
		int dd=d1*n2;
		int nn=n1*d2;
		String str=Integer.toString(nn)+"/"+Integer.toString(dd);
		str=simplify(str);
		return str;
	}
	//return the quotient of two numbers
	
	
	public static String evaluate(String st)
	{
		/*Evaluates an postfix expression using recursive methods
		 *Numbers and Operators are separated by spaces
		 *For example: evaluate("1 2 + 3 -") returns "0"
		 */
		if(st.indexOf("error")!=-1)
			return "error";
		if(st.indexOf(" 0 /")!=-1)
			return "error";
		//stop if dividing by 0
		
		if(isnumber(st))return st;
		
		/*This segment creates a checkboard that records the class of each thing
		 *in the expression (number / operator), and puts an n for number and an o
		 *for operator.
		 *
		 *An ArrayList ilist stores the position of the spaces in the expression.
		 */
		String checkboard="n";//The first thing must be a number.
		ArrayList<Integer> ilist=new ArrayList<Integer>();
		for(int i=0;i<st.length();i++)
			if(st.charAt(i)==' ')
			{
				if(i==st.length()-2)checkboard+="o";//avoids ArrayOutOfBoundsException: the last thing must be an operator.
				else 
				{
					char ch=st.charAt(i+1);
					if(((ch=='+')||(ch=='-')||(ch=='*')||(ch=='/'))&&(st.charAt(i+2)==' '))checkboard+="o";
					else checkboard+="n";
				}
				ilist.add(i);
			}
			
			
		int ind=checkboard.indexOf("nno");
		/*Find a substring that contains two numbers and an operator,
		 *so that it can be evaluated and replaced by a number.
		 *This segment of the expression is stored in number2 in line 173.*/
		 
		int indx1=0;//the position of first digit of number2
		if(ind>0)indx1=ilist.get(ind-1)+1;
		int indx2=ilist.get(ind+1);//the position of the ' ' that is before number2's operator
		String number2=st.substring(indx1,indx2+2);
		
		//This segment splits number2 into separate numbers n2_1 and n2_2, and an operator.
		char operator=number2.charAt(number2.length()-1);
		int indspaceinn2=number2.indexOf(" ");
		String n2_1=number2.substring(0,indspaceinn2);
		String n2_2=number2.substring(indspaceinn2+1,number2.length()-2);
		
		//Evaluating number2 to update its value.
		switch(operator)
		{
			case '+':
				number2=sum(n2_1,n2_2);
				break;
			case '-':
				number2=difference(n2_1,n2_2);
				break;
			case '*':
				number2=product(n2_1,n2_2);
				break;
			case '/':
				number2=quotient(n2_1,n2_2);
				break;
		}
		
		
		//Placing number2 back into the original expression, and evaluate recursively.
		String number1=st.substring(0,indx1)+number2+st.substring(indx2+2);
		return evaluate(number1);
	}
	public static void print(String st)
	{
		/*This method is basically the same as public static String evaluate,
		 *but it prints each number2, which is each step of the calculation.*/
		if(st.indexOf("error")!=-1)return;
		if(st.indexOf(" 0 /")!=-1)return;
		if(st.equals("No solution"))return;
		if(isnumber(st))return;
		String checkboard="n";
		ArrayList<Integer> ilist=new ArrayList<Integer>();
		for(int i=0;i<st.length();i++)
			if(st.charAt(i)==' ')
			{
				if(i==st.length()-2)checkboard+="o";
				else 
				{
					char ch=st.charAt(i+1);
					if(((ch=='+')||(ch=='-')||(ch=='*')||(ch=='/'))&&(st.charAt(i+2)==' '))checkboard+="o";
					else checkboard+="n";
				}
				ilist.add(i);
			}
		int ind=checkboard.indexOf("nno");
		int indx1=0;
		if(ind>0)indx1=ilist.get(ind-1)+1;
		int indx2=ilist.get(ind+1);
		String number2=st.substring(indx1,indx2+2);
		char operator=number2.charAt(number2.length()-1);
		int indspaceinn2=number2.indexOf(" ");
		String n2_1=number2.substring(0,indspaceinn2);
		String n2_2=number2.substring(indspaceinn2+1,number2.length()-2);
		switch(operator)
		{
			case '+':
				number2=sum(n2_1,n2_2);
				System.out.println(n2_1+" + "+n2_2+" = "+number2);
				break;
			case '-':
				number2=difference(n2_1,n2_2);
				System.out.println(n2_1+" - "+n2_2+" = "+number2);
				break;
			case '*':
				number2=product(n2_1,n2_2);
				System.out.println(n2_1+" * "+n2_2+" = "+number2);
				break;
			case '/':
				number2=quotient(n2_1,n2_2);
				System.out.println(n2_1+" / "+n2_2+" = "+number2);
				break;
		}
		String number1=st.substring(0,indx1)+number2+st.substring(indx2+2);
		print(number1);
	}
}