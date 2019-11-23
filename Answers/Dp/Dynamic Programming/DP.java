import java.io.*;
import java.util.Map; 
import java.util.HashMap;

class DP 
{ 
    static int coin_count = 0;
    static int count(int coins[], int n, int total, Map<Integer, Integer> map) 
    { 
        if (total == 0) 
        {
            return 0; 
        }   

        if (total < 0)
        {
            return Integer.MAX_VALUE;
        }

        if(map.containsKey(total))
            return map.get(total);
        
        int min_coin = Integer.MAX_VALUE;
        //Loop through each coin starting from highest
        //to get the coin count
        for(int i = n - 1; i >= 0; i--)
        {
            int coin = count(coins, n, total - coins[i], map);
            if(coin != Integer.MAX_VALUE && coin + 1 < min_coin)
                min_coin = coin + 1;
        }
        map.put(total, min_coin);
        return min_coin;
    } 
    
	public static void main(String[] args) 
	{ 
        int arr[] = {1, 2, 4};
        Map<Integer, Integer> map = new HashMap<>();
		System.out.println(count(arr, arr.length, 4, map)); 
	} 

} 

