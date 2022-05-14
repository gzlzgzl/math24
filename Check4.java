import java.util.*;
public class Check4 {
	
	 public  String TwoNumber(String equation) {
			equation=equation.replace("10", "(1+9)");
			equation=equation.replace("11", "(2+9)");
			equation=equation.replace("12", "(3+9)");
			equation=equation.replace("13", "(4+9)");
		return equation;  
	 }
	 public static boolean Checknumber(int n1,int n2,int n3,int n4,String equation) {
	 	 if(equation.length()<7){
	 	 	return false;
	 	 }
		 String copy=equation;
		 for(int i=0;i<copy.length();i++){
	 	 	if("1234567890+-*/()".indexOf(copy.charAt(i))==-1){
	 	 		return false;
	 	 	}
	 	 }
		 copy=copy.replace("+"," ");
		 copy=copy.replace("-"," ");
		 copy=copy.replace("*"," ");
		 copy=copy.replace("/"," ");
		 copy=copy.replace("("," ");
		 copy=copy.replace(")"," ");
		 while(copy.indexOf("  ")!=-1){
		 	copy=copy.replace("  "," ");
		 }
		 if(copy.charAt(copy.length()-1)==' '){
		 	copy=copy.substring(0,copy.length()-1);
		 }
		 //System.out.println("copy="+copy);
		 ArrayList<Integer> nums=new ArrayList<Integer>();
		 for(String string: copy.split(" ")){
		 	if(string.length()>2){
		 		return false;
		 	}
		 	if(string.length()>0){
		 		nums.add(Integer.parseInt(string));
		 	};
		 }
		 if(nums.size()!=4){
		 	return false;
		 }
		 Collections.sort(nums);
		 if((nums.get(0)<1)||(nums.get(3)>13)){
		 	return false;
		 }
		 ArrayList<Integer> ns=new ArrayList<Integer>();
		 ns.add(n1);ns.add(n2);ns.add(n3);ns.add(n4);
		 Collections.sort(ns);
		 for(int i=0;i<4;i++)
		 	if(nums.get(i)!=ns.get(i)){
		 		return false;
		 	}
		 return true;
	 }
	 //line 56 to line 66 is copied from http://www.voidcn.com/article/p-rqeswueb-bbx.html
	 //get the priority
	 public  boolean getPriority(String stackPeek,String current)
	    {
	        if(("*/".indexOf(stackPeek)!=-1) && ("+-*/".indexOf(current)!=-1))
	        {
	            return true;
	        }else if(("+-".indexOf(stackPeek)!=-1) && ("+-".indexOf(current)!=-1))
	        {
	            return true;
	        }
	        return false;
	    }
	 public  ArrayList<String> getArrayList(String equation)
	 {
		 ArrayList<String> Po = new ArrayList<String>();
	        for(char chs:equation.toCharArray())
	        {
	              Po.add(chs+"");
	        }
	        return Po;
	 }
		 //the idea of first taking the ( into the stack and push everything following into it until hit the )
	     //then pop ( out is from https://blog.csdn.net/hbkc5/article/details/53065440
	 	//and the language about how to use stack is from https://www.runoob.com/java/java-stack-class.html
	 public  ArrayList<String> Sequence(ArrayList<String> o) throws Exception 
	    {
	        ArrayList<String> l = new ArrayList<String>();
	        Stack<String> operator = new Stack<String>();
	        //As we are dealing with a string, we need to seperate everthing
	        for(String chs:o)
	        if(Character.isDigit(chs.charAt(0)))
	        {
	            l.add(chs);
	        }else{
	            switch (chs.charAt(0)) {
	            //dealing with the "()"
	            case '(':
	                operator.push(chs);
	                break; 
	            case ')':
	                while(!"(".equals(operator.peek()))
	                       l.add(operator.pop());
	                operator.pop();
	                break;
	            default:
	                //I had a bug here and honestly I do not understand line 92 to line 95 even now. Give credit 
	            	//to a friend from China for helping me dealing with this bug
	                while(!operator.isEmpty() && getPriority(operator.peek(),chs))
	                    l.add(operator.pop());
	                operator.push(chs);
	                break;
	            }
	        }
	        while(!operator.isEmpty())
	            l.add(operator.pop());
	        return l;
	    }
	 	//the final method  for the answer
	 public  String remove(String s, int p) {
	      return s.substring(0, p) + s.substring(p + 1);
	   }
	 public  String FinalCheck(String equation) throws Exception {
			//the idea is:1)turn the STring into arraylist;2)use stack for the sequence, ()>*=/>+=-;3)use switch
			//to come up with the answer
			//idea:https://www.liaoxuefeng.com/wiki/1252599548343744/1265121668997888
			if(equation.length()==0){
				return "0";
			}
			if(equation.charAt(0)=='-'){
				return(FinalCheck('0'+equation));
			}
			ArrayList<String> u=Sequence(getArrayList(TwoNumber(equation)));
			String Newsequence=u.toString();
			Newsequence=remove(Newsequence, 0);
			Newsequence=remove(Newsequence, Newsequence.length()-1);
			Newsequence=Newsequence.replace(",", "");
	        return Newsequence;
	        
		//	int result24 = answer(Sequence(getArrayList(equation)));
			//return result24;
		}
		
}

