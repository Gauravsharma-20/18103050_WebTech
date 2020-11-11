public class Solution2 extends Thread{
	
	
	public int initial_value;
	static int max_divisors=0,value;
	
	//assign the initial value from where this thread will start to check number of divisors
	public void initialize(int params) {
		this.initial_value = params;
		this.start();
		
	}
	
	//check the number of divisors within the range alloted to each thread
	public void run() {
		for(int i=this.initial_value+1;i<=this.initial_value+25000;i++)
		{
			int divisors = 0;
			for(int j=1;j<=i;j++)
			{
				if(i%j==0)
				{
					divisors = divisors + 1;
				}
			}
			//check if this number has maximum divisors
			maximum_divisors(divisors,i);
			
		}
		
	}
	//critical section to check if the number of divisors are maximum or not
	synchronized public void maximum_divisors(int divisors,int number)
	{
		if(divisors > max_divisors)
		{
			max_divisors = divisors;
			value = number;
		}
	}
	public static void main(String[] args) {
		Solution2 thread1 = new Solution2();
		Solution2 thread2 = new Solution2();
		Solution2 thread3 = new Solution2();
		Solution2 thread4 = new Solution2();
		thread1.initialize(0);
		thread2.initialize(25000);
		thread3.initialize(50000);
		thread4.initialize(75000);
		try {
			//wait for each thread to execute
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
		}catch (Exception e) 
		{
            e.printStackTrace();
        }
		System.out.println("Maximum number of divisors are of "+value+" and the count is "+max_divisors);
		

	}

}
