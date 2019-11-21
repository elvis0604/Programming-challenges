import java.io.*; 

class DP 
{ 
    static int coin_count = 0;
    static int count(int coins[], int n, int C) 
    { 
        if (C == 0) 
        {
            return 0; 
        }   

        if (C < 0)
        {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        
        //Loop through each coin starting from highest
        //to get the coin count
        for(int i = n - 1; i >= 0; i--)
        {
            int temp = count(coins, n, C - coins[i]);
            if(temp + 1 < res)
                res = temp + 1;
        }
        return res;
    } 
    
	
	// Driver program to test above function 
	public static void main(String[] args) 
	{ 
        int arr[] = {1, 2};
		System.out.println(count(arr, arr.length, 4)); 
	} 

} 

// This article is contributed by vt_m. 
