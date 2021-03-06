package app;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import grading.DropFilter;
import grading.Filter;
import grading.Grade;
import grading.GradingStrategy;
import grading.Missing;
import grading.SizeException;
import grading.TotalStrategy;

import grading.WeightedTotalStrategy;



class GradientTest
{
	Gradient g = new   Gradient();

	@Test
	public void calgrades() throws Exception 
	{
	   
		String [] args = {"10.0", "10.0", "5.0", "15.0", "5.0", "20.0", "20.0", "0.0", "0.0", "10.0", "15.0", "60.0", "45.0"};
	      Gradient.main(args);
	   
			
		}
		
	@Test
	public void constructor_null() throws Exception
    {
		 String args[] = {"10.0"};
		 double len= args.length;
		 Gradient.main(len);		
    }
	@Test
	public void test1() throws Exception  
	{
		
        String [] args = {null, "10.0", "5.0", null, "5.0", "20.0", "20.0", "0.0", "0.0", "10.0", "15.0", "60.0", "45.0"};
        Gradient.main(args);
        
    }
	@Test
	public void test2() throws Exception  
	{
		
        String [] args = {null, "10.0", "10.0", "", "5.0", "20.0", "20.0", "0.0", "0.0", "10.0", "15.0", "60.0", "45.0"};
        Gradient.main(args);
        
    }
	@Test
    public void getKey()
    {
        double value=56.98;
        String key ="str";
        Grade card = new Grade(key);   
        assertEquals("grade",key, card.getKey());      
    }
	@Test
	 public void getKey2() throws IllegalArgumentException
	 {
	     
	     Double value=null;
	     String key ="";
	     Grade card2 = new Grade(key,value);
	     
	  // assertEquals("grade",key, card2.getKey(),0.0001);      
	 }
	 @Test
	 public void getValue()
	    {
	        double value=56.98;
	        String key ="str";
	        Grade card = new Grade(key,value);
	        
	        assertEquals("grade",value, card.getValue(),0.0001);      
	    }
	 @Test
	 public void doubleValue()
    {
	     double value=0.0;
	     String key ="str";
	     double number=0.0; 
	     String missing="number";
	     double missingValue=9.0;
	     Missing card = new Missing();
	     assertEquals("grade",number,card.doubleValue(number, missingValue),0.0001); 
	 }

	 @Test
	 public void doubleValue2()
	 {
	     double value=0.0;
	     String key ="str";
	     double number=0.0; 
	     String missing="number";
	     double missingValue=9.0;
	     Missing card = new Missing();
	     assertEquals("grade",missing,card.doubleValue(null, missingValue));      

	     //assertEquals("grade",number,card.doubleValue(number, missingValue),0.0001); 

	 }

	 @Test
	 public void doubleValue3()
	 {
	     double missing=0.0;
	     String key ="str";
	     double number=0.0; 
	     double missingValue=9.0;
	     Missing card = new Missing();
	     assertEquals("grade",missing,card.doubleValue(null),0.0001);      
	 }
	 
   @Test
   public void Exception()  throws java.lang.Exception
   {
	 String Message = "Hello";
	 
	 SizeException e = new SizeException(Message);
	 
   }
   
   @Test
   public void Exception2()  throws java.lang.Exception
   {
 	 String Message = "Hello";  	 
  	 SizeException e = new SizeException(); 	 
   }
 
   @Test
   public void applyTest() throws SizeException
  {
	 
	    Filter                      paFilter;        
		Grade                    courseGrade, hwGrade, paGrade;
		GradingStrategy          courseStrategy, hwStrategy, paStrategy;
		List<Grade>              grades, hws, pas;
		Map<String, Double>      courseWeights;
		String[] args= {"10.0", "10.0", "5.0", "15.0", "5.0", "20.0", "20.0", "0.0", "0.0", "10.0", "15.0", "60.0", "45.0"};
		

		// Create the filter and strategy for PAs
		paFilter   = new DropFilter(true, false);
		paStrategy = new TotalStrategy(); 

		// Create the strategy for HWs
		hwStrategy = new TotalStrategy();

		// Create the weights and strategy for the course grade
		courseWeights = new HashMap<String, Double>();
		courseWeights.put("PAs",     0.4);
		courseWeights.put("HWs",     0.1);
		courseWeights.put("Midterm", 0.2);
		courseWeights.put("Final",   0.3);
		courseStrategy = new WeightedTotalStrategy(courseWeights);

		try
		{
			// Put the PA grades in a List
			pas = new ArrayList<Grade>();
			for (int i=0; i<6; i++) 
			{
				pas.add(parseGrade("PA"+(i+1), args[i]));
			}

			// Calculate the PA grade (after filtering)
			paGrade = paStrategy.calculate("PAs", Filter.apply(pas));

			// Put the HW grades in a List 
			hws = new ArrayList<Grade>();
			for (int i=0; i<5; i++)
			{
				hws.add(parseGrade("HW"+(i+1), args[i+6]));
			}

			// Calculate the HW grade
			hwGrade = hwStrategy.calculate("HWs", hws);

			// Put all of the grades in a List
			grades = new ArrayList<Grade>();
			grades.add(paGrade);
			grades.add(hwGrade);
			grades.add(parseGrade("Midterm", args[11]));
			grades.add(parseGrade("Final",   args[12]));
		 	 
	 
     DropFilter  e = new DropFilter();
	 
     
     assertEquals("grade",grades,e.apply(grades));      
     //assertEquals("grade",number,card.doubleValue(number, 
	
    }catch (SizeException se)
		{
		System.out.println("You entered too few valid grades.");
	    }
	catch (IllegalArgumentException iae)
	   {
		// Should never get here since all keys should be valid
	   } 
  }
	@Test
	public void compareC1()
	{
		Grade grade1 = new Grade("H.W",10.0);
		Grade grade2 = new Grade("H.W",null);
		assertEquals("",-1,grade2.compareTo(grade1));
	}
	@Test
	public void compareC2() 
	{
		
		Grade grade3 = new Grade("H.W",null);
		Grade grade4 = new Grade("H.W",null);
		assertEquals(0,grade3.compareTo(grade4));	
	}
	@Test
	public void compareC3() 
	{
		Double b = null;
		Double c= null;
		Grade grade5 = new Grade("H.W",null);
		Grade grade6 = new Grade("H.W",10.0);
		assertEquals(-1,grade5.compareTo(grade6));
	}
	 @Test
		public void testIllegalException() 
	 {
			assertThrows( IllegalArgumentException.class, () -> { Grade grade =new Grade("",9.0); } );
	 }
	 @Test
	 public void nullkey() throws IllegalArgumentException
	 {
		 String x="";
		 double g=89;
	    new Grade("",89);
	 }

	 @Test
	 public void nul() throws IllegalArgumentException
	 {
		 String x="";
		 double g=89;
	    new Grade("");
	 }
	 

 static Grade parseGrade(String key, String value) throws IllegalArgumentException
	{
		Grade  result;		
		try
		{
			Double v;
			if (value == null)
				v = null;
			else 
				v = new Double(Double.parseDouble(value));
			
			result = new Grade(key, v);
		}
		catch (NumberFormatException nfe)
		{
			result = new Grade(key, null);
		}		
		return result;
	} 
}
