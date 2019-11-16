class Prime
{
	static void isPrime(int start, int end)
	{
		boolean flag = false;
		int i, j;
		
		if(start == 0 || start == 1)
			start = 2;
		
		for(i = start; i <= end; i++)
		{
			for(j = 2; j <= i/2; j++)
			{
				if(i % j == 0)
				{
					flag = true;
					break;
				}
			}
			if(!flag)
				System.out.println("Prime is: " + i);
			flag = false;
		}
	}
	
	public static void main(String[] args)
	{
		int start = 1;
		int end = 20;
		
		isPrime(start, end);
	}
}