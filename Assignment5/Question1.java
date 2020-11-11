
public class Solution1 implements Runnable {
	
	public void run() {
		for(int i=1;i<=100;i++)
		{
			System.out.print(i+" ");
			if(i%10==0)
			{
				System.out.println("Count multiple of 10");
			}
			try
			{
				Thread.sleep(1000);
	        } catch (Exception e) 
			{
	            e.printStackTrace();
	        }
			
		}
		
	}

	public static void main(String[] args) {
		Solution1 obj1 = new Solution1();
		Thread thread = new Thread(obj1);
		thread.start();

	}

}


//output : -
//1 2 3 4 5 6 7 8 9 10 Count multiple of 10
//11 12 13 14 15 16 17 18 19 20 Count multiple of 10
//21 22 23 24 25 26 27 28 29 30 Count multiple of 10
//31 32 33 34 35 36 37 38 39 40 Count multiple of 10
//41 42 43 44 45 46 47 48 49 50 Count multiple of 10
//51 52 53 54 55 56 57 58 59 60 Count multiple of 10
//61 62 63 64 65 66 67 68 69 70 Count multiple of 10
//71 72 73 74 75 76 77 78 79 80 Count multiple of 10
//81 82 83 84 85 86 87 88 89 90 Count multiple of 10
//91 92 93 94 95 96 97 98 99 100 Count multiple of 10
