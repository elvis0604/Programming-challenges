import java.io.*; 

class DP 
{ 
    static int coin_count = 0;
    static int count(int coins[], int n, int total) 
    { 
        if (total == 0) 
        {
            return 0; 
        }   

        if (total < 0)
        {
            return Integer.MAX_VALUE;
        }

        int min_coin = Integer.MAX_VALUE;
        
        //Loop through each coin starting from highest
        //to get the coin count
        for(int i = n - 1; i >= 0; i--)
        {
            int temp = count(coins, n, total - coins[i]);
            if(temp != Integer.MAX_VALUE && temp + 1 < min_coin)
                min_coin = temp + 1;
        }
        return min_coin;
    } 
    
	public static void main(String[] args) 
	{ 
        int arr[] = {1, 2, 4, 5};
		System.out.println(count(arr, arr.length, 8)); 
	} 

} 

