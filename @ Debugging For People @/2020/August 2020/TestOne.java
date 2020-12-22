// August 27, 2020 | August 30, 2020
// I'm trying to get the number of times bob occurs in s.
// For example,if s="bobobobob",
// it should print 4. I have: 
//     static int problemTwo(String s) {
//         int answer=0;
//         String stringFind="bob";
//         int lastIndex=0;
        
//         while(lastIndex!=-1){
//             lastIndex=s.indexOf(stringFind,lastIndex);

//         if(lastIndex!=-1){
//             answer++;
//             lastIndex+=stringFind.length();
//         }
//     }
//     return answer;
    
// but that only gives me 2

public class TestOne {
    public static void main(String[] args) {
        problemTwo("bobbobbobbob");
    }

    static int problemTwo(String s){
        int answer = 0;
        String stringFind = "bob";
        int lastIndex = 0;

        while(lastIndex != -1){
            lastIndex = s.indexOf(stringFind,lastIndex);

            if(lastIndex != -1){
                answer++;
                lastIndex += stringFind.length();
            }
        }
        System.out.println(answer);
        return answer;
    }
}

// You may have already figure this out but - no,
// it should not have print 4,
// because it is returning the first occurence of 'bob' which is ['bob'o'bob'], 
// in this case - that is what this method is interpreting. 
// If string s = "bobbobbobbob", then it will return 4.