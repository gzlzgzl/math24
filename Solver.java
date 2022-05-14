import java.io.*;
import java.util.*;
public class Solver
{
	public Solver(){}
	//contains the main process of generating expressions from numbers and evaluating them.
	public Calculator TI84=new Calculator();
	
	/*There are 5 kinds of postfix expressions of length 4 regarding the operator's positions.
	 *The following methods (type1 to type5) lists each kind and 
	 *generates an expression of the kind with numbers a,b,c,d and operators c1,c2,c3.
	 *They also use the calculator to evaluate the expression and check if it equals 24.*/
	public String type1(int a,int b,int c,int d,char c1, char c2, char c3)
	{   
		//a b * c * d *
		String ans=Integer.toString(a)+' '+Integer.toString(b)+' '+c1+' '
		+Integer.toString(c)+' '+c2+' '+Integer.toString(d)+' '+c3;
		if(TI84.evaluate(ans).equals("24"))return ans;
		else return "failure";
	}   
	public String type2(int a,int b,int c,int d,char c1, char c2, char c3)
	{   
		//a b * c d * *
		String ans=Integer.toString(a)+' '+Integer.toString(b)+' '+c1+' '
		+Integer.toString(c)+' '+Integer.toString(d)+' '+c2+' '+c3;
		if(TI84.evaluate(ans).equals("24"))return ans;
		else return "failure";
	}
	public String type3(int a,int b,int c,int d,char c1, char c2, char c3)
	{
		//a b c * * d *
		String ans=Integer.toString(a)+' '+Integer.toString(b)+' '
		+Integer.toString(c)+' '+c1+' '+c2+' '+Integer.toString(d)+' '+c3;
		if(TI84.evaluate(ans).equals("24"))return ans;
		else return "failure";
	}
	public String type4(int a,int b,int c,int d,char c1, char c2, char c3)
	{
		//a b c * d * *
		String ans=Integer.toString(a)+' '+Integer.toString(b)+' '
		+Integer.toString(c)+' '+c1+' '+Integer.toString(d)+' '+c2+' '+c3;
		if(TI84.evaluate(ans).equals("24"))return ans;
		else return "failure";
	}
	public String type5(int a,int b,int c,int d,char c1, char c2, char c3)
	{
		//a b c d * * *
		String ans=Integer.toString(a)+' '+Integer.toString(b)+' '
		+Integer.toString(c)+' '+Integer.toString(d)+' '+c1+' '+c2+' '+c3;
		if(TI84.evaluate(ans).equals("24"))return ans;
		else return "failure";
	}
	
	
	
	//This method stores result from all kinds derived from the same entries.	
	public String[] search(int a,int b,int c,int d,char c1, char c2, char c3)
	{
		String[] result=new String[5];
		result[0]=type1(a,b,c,d,c1,c2,c3);
		result[1]=type2(a,b,c,d,c1,c2,c3);
		result[2]=type3(a,b,c,d,c1,c2,c3);
		result[3]=type4(a,b,c,d,c1,c2,c3);
		result[4]=type5(a,b,c,d,c1,c2,c3);
		return result;
	}
	 
	
	/*This method traverses the 24 possible orders of the 4 numbers
	 *with 64 combinations of operators respectively. 
	 *Calculations stop if a valid result that equals 24 is found.*/
	final String operators="+-*/";
	public String solve(int a, int b, int c, int d)
	{
		String[][] results=new String[24][5];
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				for(int k=0;k<4;k++)
				{
					results[0]=search(a,b,c,d,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[1]=search(a,b,d,c,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[2]=search(a,c,b,d,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[3]=search(a,c,d,b,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[4]=search(a,d,b,c,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[5]=search(a,d,c,b,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					
					results[6]=search(b,a,c,d,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[7]=search(b,a,d,c,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[8]=search(b,c,a,d,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[9]=search(b,c,d,a,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[10]=search(b,d,a,c,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[11]=search(b,d,c,a,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					
					results[12]=search(c,a,b,d,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[13]=search(c,a,d,b,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[14]=search(c,b,a,d,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[15]=search(c,b,d,a,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[16]=search(c,d,a,b,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[17]=search(c,d,b,a,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					
					results[18]=search(d,a,b,c,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[19]=search(d,a,c,b,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[20]=search(d,b,a,c,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[21]=search(d,b,c,a,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[22]=search(d,c,a,b,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					results[23]=search(d,c,b,a,operators.charAt(i),operators.charAt(j),operators.charAt(k));
					
					for(int m=0;m<24;m++)
						for(int n=0;n<5;n++)
							if(!(results[m][n].equals("failure")))
								return results[m][n];
								
				}
		return "No solution";
	}
}