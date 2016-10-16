//Name : Yash Thakkar
// Id  : N03312382
// Assignment 2

/*This compiler is for
S -> aSd
S -> AB
A -> cA
A -> lambda
B -> bB
B -> c*/

import java.util.*;

class TopDownParser{
  public static void main(String[] args){
    ArgsTokenMgr tm = new ArgsTokenMgr(args);
    StackParser parser = new StackParser(tm);
    parser.parse();
  }
}

class ArgsTokenMgr{
  private int index;
  String input;
  public ArgsTokenMgr(String[] args){
    if(args.length>0){
      input = args[0];
    }
    else{
      input = "";
      index = 0;
      System.out.println("Input = " + input);
    }
  }
  public char getNextToken(){
    if(index < input.length()){
      return input.charAt(index++);
    }
    else{
      return '#';
    }
  }
}

class StackParser{
  private ArgsTokenMgr tm;
  private Stack<Character> stk;
  private char currentToken;

  public StackParser(ArgsTokenMgr tm){
    this.tm = tm;
    advance();

    stk = new Stack<Character>();
    stk.push('$');
    stk.push('S');
  }

  private void advance(){
    currentToken = tm.getNextToken();
  }

  public void parse(){
    boolean done = false;

    while(!done){
      switch(stk.peek()){
        case 'S':
          if(currentToken == 'a'){
            stk.pop();
            stk.push('d');
            stk.push('S');
            advance();
          }else if(currentToken == 'c' && tm.input.charAt(2) == 'd'){

            stk.pop();
            advance();
          }
          else if(currentToken == 'c'){
            stk.pop();
            stk.push('B');
            stk.push('A');
            advance();
          }
          else if(currentToken == 'b'){
            stk.pop();
            stk.push('B');
            advance();
          }
          else
            done = true;
            break;

        case 'A':
          if(currentToken == 'c'){
            advance();
          }
          else if(currentToken == 'b'){
            stk.pop();
            advance();
          }
          else
            done = true;
            break;
        case 'B':
              if(currentToken == 'b'){
                advance();
              }
              else if(currentToken == 'c'){
                stk.pop();
                advance();
              }
              else
                done = true;
                break;

        case 'a':
        case 'b':
        case 'c':
        case 'd':
          if(stk.peek().charValue() == currentToken){
            stk.pop();
            advance();
          }
          else{
            done = true;
            break;
          }

        case '$':
          done = true;
          break;
      }
    }
    if (currentToken =='#' && stk.peek() == '$')
    System.out.println(tm.input + " : accept");
  else
    System.out.println(tm.input + " : reject");
  }
}
