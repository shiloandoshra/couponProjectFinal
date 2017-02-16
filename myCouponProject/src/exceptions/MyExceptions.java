package exceptions;
/**
 * The class MyExceptions is part of myCouponProject
 * The class MyExceptions deals with all the exceptions
 * @author Oshra & Shilo
 *
 */
public class MyExceptions{
	/**
	 * method Exceptions deals with the different exceptions 
	 * @param Exception e stores the automatic built in exceptions  
	 * @param String problem stores the name of the user which has the problem
	 */
	
	public static void Exceptions(Exception e, String problem) {
		String exceptions[] = e.getClass().toString().split(" ");
		String exception = exceptions[1];
		
		switch (exception){
			case ("DuplicateUserException"):
			
				System.out.println("sorry,"+problem+" is already in the system");
				break;
			case ("CustomerHasCouponAlready"):
				
				System.out.println("sorry,"+problem+" already has this coupon");
				break;
				
			case ("NullPointerException"):
				
				System.out.println("sorry,"+problem+" is not in the system");
				break;
			case("java.lang.SQLException"):
				
				System.out.println(e.getCause());
				System.out.println("cannot connect with mysql");
				break;
				
			case ("java.lang.InterruptedException"):

				System.out.println(e.getCause());
				System.out.println("the thread has been interrupted");
				break;

			case ("java.lang.ClassNotFoundException"):

				System.out.println(e.getCause());
				System.out.println(problem+" not found");
				break;		
			case ("WrongDataInputException"):

				System.out.println("the data input cannot be proccesed");
				break;	
					
			case ("DuplicateCouponTypeException"):
			System.out.println("this coupon already exists");
			break;	
			
			case ("ParseException"):
				System.out.println("problem with the date");
				break;	
			
			case ("UnavailableCouponException"):
				System.out.println("this coupon ran out, sorry you can not purchase this coupon");
				break;
		}
	}	
				
		/**
		 * method Exceptions deals with only the built in exceptions 
		 * @param Exception e stores the automatic built in exceptions
		 */
		public static void Exceptions(Exception e) {
			String exceptions[] = e.getClass().toString().split(" ");
			String exception = exceptions[1];
			
			switch (exception){
			
			case ("UnavailableCouponException"):
				System.out.println("this coupon ran out, sorry you can not purchase this coupon");
				break;
			case ("CustomerHasCouponAlready"):
				
				System.out.println("sorry,this customer already has this coupon");
				break;
			case("java.lang.SQLException"):
				
				System.out.println(e.getCause());
				System.out.println("cannot connect with mysql");
				break;
				
			case ("java.lang.InterruptedException"):

				System.out.println(e.getCause());
				System.out.println("the thread has been interrupted");
				break;

					
			case ("WrongDataInputException"):

				System.out.println("the data input cannot be proccesed");
				break;	
					
			case ("DuplicateCouponTypeException"):
			System.out.println("this coupon already exists");
			break;	
			
			case ("ParseException"):
				System.out.println("problem with the date");
				break;
						
					
			}
		
	}			
}
