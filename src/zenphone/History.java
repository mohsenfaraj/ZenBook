package zenphone;

import java.util.Stack;

public class History {
    Stack<String> stack = new Stack<>();
    
    public void add(String text){
        stack.push(text);
    }
    
    public String get(){
        if (!stack.isEmpty()){
            return stack.pop() ;
        }
        return "" ;
    }
}
