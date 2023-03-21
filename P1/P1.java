public class P1
{
    public static void main(String[] args)
    {   
	
    }
    
    public static int stringValue(String word)
    {
        int sum = 0;
        char newChar;
        char maxChar = ' ';
        boolean last = false;
        
        for (int i = 0; i < word.length(); ++i) {
            newChar = word.charAt(i);
            
			// checks if current char is not a number and is the maximum ascii value
            if (newChar > maxChar && (newChar < 48 || newChar > 57)) {
                maxChar = newChar;
            }
            
			//checks for doubles while permitting double doubles
            if (i >= 1) {
                char prevChar = word.charAt(i-1);
                if (newChar == prevChar && last == false) {
                    last = true;
                    continue;
                }
                else {
                    last = false;
                }
            }
            // if it is a space, char is skipped and next char iterates
            if (newChar == 32) {
                continue;
            }
            // checks if the char is not a number and adds it to the sum
            if (newChar < 48 || newChar > 57) {
                sum += newChar;
            }
        }
		// goes through string one last time and multiplies maxChar by numbers in the string
        for (int j = 0; j < word.length(); ++j) {
            if (word.charAt(j) >= 48 && word.charAt(j) <= 57 && maxChar != ' ') {
                sum += word.charAt(j) * maxChar;
            }
        }
        return sum;
    }
    
    public static double expValue(int x, double precision)
    {
        double ex;
		// for negative exponents
        if (x < 0) {
            ex = 1;
            double nextSum;
            double factProd = 1;
            int j = 1;
            while (true) {
                nextSum = Math.pow(x, j)/(factProd);
                if (nextSum < 0) {
                    nextSum *= -1;
                }
                if (nextSum < precision) {
                    break;
                }
                ex += Math.pow(x, j)/factProd;
                j += 1;
                factProd *= j; 
            }
        }
        // for positive exponents
        else { 
            ex = 1;
            double nextSum;
            double factProd = 1;
            int i = 1;
            while (true) {
                
                factProd *= i;
                nextSum = Math.pow(x, i)/(factProd);
                if (nextSum < precision) {
                    break;
                }
                ex += Math.pow(x, i)/factProd;
                i += 1;
            }
        }
        
        if (ex < 0) {
            return precision + ex;
        }
        
        return ex;
    }
    
    public static int mirrorNum(int num)
    {   
        int rev = 0;
        while (num != 0) {
    
            // gets the last digit from num and adds it to the front of sum
            int x = num % 10;
            rev = rev * 10 + x;

            // removes the last digit from num
            num /= 10;
        }
        return rev;
    }
    
    public static boolean raisedNum(long num)
    {   
        // loops go to 64 because max long type value is 2^63 - 1
		for (long i = 2; i < 64; ++i)
        {
            for (long j = 2; j < 64; ++j)
            {
				// checks if i^j + j^i is > max value of a long b/c if it is the return value will be true even if it is not. 
                if (Math.pow(i,j) + Math.pow(j,i) > 9223372036854775807L) {
                    break;
                }
                long sum = (long)(Math.pow(i, j) + Math.pow(j, i));
                
                if (sum == num)
                {
                    return true;
                }
                else if (sum > num)
                {
                    break;
                }
            }
        }
        return false;
    }

    public static int[][] smallestSubarray(int[][] array, int sum)
    {
        int maxSum = sum - 1;
        int newSum = 0;
        int count = 0;
        boolean cont = true;
        int[][] retArray = new int[2][2];
        while (maxSum < sum) {
            
            count += 1;
            retArray = new int[count+1][count+1];
            
            for (int row = 0; row < array.length - count; ++row)
            {
                for (int col = 0; col < array[row].length - count; ++col)
                {   
                    cont = true;
                    newSum = array[row][col];
                    for (int i = 1; i < retArray.length; ++i)
                    {
                        newSum += array[row+i][col];
                        newSum += array[row+i][col+i];
                        newSum += array[row][col+i];
                        
                        if (retArray.length > 2 && cont == true)
                        {
                            for (int j = 1; j < count; ++j)
                            {
                                if (j + i + row > retArray.length || i + j >= retArray.length) {
                                    cont = false;
                                    break;
                                }
                                newSum += array[row+i+j][col+i];
                                newSum += array[row+i][col+i+j];
                            }
                        }
                    }                   
                    
                    if (newSum > maxSum) {
                        maxSum = newSum;
                        for (int x = 0; x < retArray.length; ++x)
                        {
                            for (int y = 0; y < retArray[x].length; ++y)
                            {
                                retArray[x][y] = array[row+x][col+y];
                            }
                        }
                    }
                }
            }
        }
        return retArray;
    }
    
    public static void replaceElement(int[][] array, int elem, int[] newElem)
    {
        
		for (int i = 0; i < array.length; ++i)
        {
            // checks number of duplicates in the current nested array
			int dupes = 0;
            for (int j = 0; j < array[i].length; ++j)
            {
                if (array[i][j] == elem) {
                    ++dupes;
                }
            }
            
			// if no dupes then next nested array is checked
            if (dupes == 0) {
                continue;
            }
            else if (dupes == 1 && newElem.length == 1) {
                for (int k = 0; k < array.length; ++k) {
                    if (array[i][k] == elem) {
                        array[i][k] = newElem[0];
                    }
                }
            }
			// executed if newElem length is 0
			else if (newElem.length == 0) {
				int newArray[] = new int[array[i].length-dupes + (dupes*newElem.length)];
				int count = 0;
				
				// goes through new array length which is length - # of dupes bc newElem is an empty array
				for (int l = 0; l < newArray.length; ++l) {
					if (array[i][l+count] == elem) {
						++count;
						// while loop checks if there are multiple replaced elements in a row
						while (array[i][l+count] == elem) {
							++count;
						}
						newArray[l] = array[i][l+count];
					}
					else {
						newArray[l] = array[i][l+count];
					}
				}
				array[i] = newArray;
			}
            
			// Executed if length of newElem > 1
			else {
                int newArray[] = new int[array[i].length-dupes + (dupes*newElem.length)];
                int count = 0;
                // checks if theres a duplicate and if there is it replaces it with the elements in newElem using the for loop
                for (int l = 0; l < newArray.length; ++l) {
                    if (array[i][l-count] == elem) {
                        for (int m = 0; m < newElem.length; ++m) {
                            newArray[l+m] = newElem[m];
                            if (m >= 1) {
                                ++count;
                            }
                        }
                        l += newElem.length - 1;
                    }
                    else {
                        newArray[l] = array[i][l-count];
                    }
                }
                array[i] = newArray;
            }
            
        }
    }

    public static int[][] removeDuplicates(int[][] array)
    {   
        int newArray[][] = new int[array.length][0];
        // first for loop finds the number of duplicates in each array and sets a new array of the length - dupes
		for (int i = 0; i < array.length; ++i)
        {
            int count = 0;
            for (int j = 0; j < array[i].length; ++j)
            {
                if (j >= 1) {
                    if (array[i][j] == array[i][j-1]) {
                        continue;
                    }
                    else {
                        count += 1;
                        newArray[i] = new int[count];
                    }
                }
                else if (i > 0 && j == 0 && array[i][j] == array[i-1][array[i-1].length-1]) {
                    continue;
                }
                else {
                    count += 1;
                    newArray[i] = new int[count];
                }   
            }
        }
        // second for loop stores the unique values into the newarray then sets the array with dupes = to array w/o dupes
        for (int k = 0; k < array.length; ++k)
        {   
            int count2 = 0;
            for (int l = 0; l < array[k].length; ++l)
            {
                if (l >= 1)
                {
                    if (array[k][l] == array[k][l-1]) {
                        count2 += 1;
                        continue;
                    }
                    else {
                        newArray[k][l - count2] = array[k][l];
                    }
                }
                else if (k > 0 && l == 0 && array[k][l] == array[k-1][array[k-1].length-1]) {
                    count2 += 1;
                    continue;
                }
                else {
                    newArray[k][l - count2] = array[k][l];
                }   
            }
        }

        // Following code removes empty arrays from newArray if applicable
        int emptyArr = 0;
        for (int l = 0; l < newArray.length; ++l) {
            if (newArray[l].length == 0) {
                emptyArr += 1;
            }
        }
        int[][] retArray = new int[newArray.length - emptyArr][0];
        int newEmpty = 0;
        for (int m = 0; m < newArray.length; ++m) {
            if (newArray[m].length != 0) {
                retArray[m - newEmpty] = newArray[m];
            }
            else {
                ++newEmpty;
            }
        }
        return retArray;
    }
    
    public static int[] vortex(int[][] array)
    {
        int newArray[] = new int[0];
        int count = 0;

		// finds the number of elements and assigns it as length to a 1D array 
        for (int i = 0; i < array.length; ++i)
        {
            for (int j = 0; j < array[i].length; ++j) {
                count += 1;
            }
        }
        newArray = new int[count];
        
        int comp = 0; 
        int loop = 0;
        int checkLoop = 0;
        boolean horizontal = true;
        boolean vertical = true;
        
		// iterates through the array
        while (comp < count)
        {   
			// every time checkloop is even loop is incremented by 1 which is used to iterate through for loops
            if (checkLoop % 2 == 0 && checkLoop != 0) {
                loop += 1;
            }
            
			// moves right
            if (horizontal == true) {
                
                for (int row = loop; row <= array[0].length - 1 - loop; ++row) {
                    newArray[comp] = array[loop][row];
                    comp += 1;
                    
                    if (comp == count) {
                        break;
                    }
                }
                horizontal = false;
            }
			// moves left
            else {
                
                for (int row = array[0].length - 1 - (checkLoop - loop); row > loop - 1; --row) {
                    newArray[comp] = array[array.length - 1 - loop][row];
                    comp += 1;
                    
                    if (comp == count) {
                        break;
                    }
                }
                horizontal = true;
            }
			// moves down
            if (vertical == true) {
                for (int col = loop + 1; col <= array.length - 1 - loop; ++col) {
                    newArray[comp] = array[col][array[0].length - 1 - loop];
                    comp += 1;
                    
                    if (comp == count) {
                        break;
                    }
                }
                vertical = false;
            }
			// moves up
            else {
                for (int col = array.length - 1 - (checkLoop - loop); col > loop; --col) {
                    newArray[comp] = array[col][loop];
                    comp += 1;
                    
                    if (comp == count) {
                        break;
                    }
                }
                vertical = true;
            }
            checkLoop += 1;
        }
		
			
        return newArray;
    }

}