/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackinfixtopostfix;

/**
 *
 * @author REVA8
 */
import java.util.*;

public class StackInfixToPostfix {

    /**
     * @param args the command line arguments
     */
    static    Stack operators = new Stack();
    public static void main(String[] args) {
        // TODO code application logic here
        String infix;
        Scanner read = new Scanner(System.in);
        System.out.print("Enter Algebraic Expression in infix form : ");
        infix = read.nextLine();

        System.out.println("Algebraic Expression in postfix form is :  " + toPostfix(infix));
        read.close();
    }

    static String toPostfix(String infix) {
     
        char symbol;
        String postfix = "";
        for (int i = 0; i < infix.length(); i++) {
            symbol = infix.charAt(i);
            System.out.println(symbol);
            if (Character.isSpaceChar(symbol)) {
                continue;
            }
            if (Character.isLetter(symbol)) {
                postfix = postfix + symbol;
            } else if (symbol == '(') {
                operators.push(symbol);
            } else if (symbol == ')') {
                while (operators.peek() != '(') {
                    postfix = postfix + operators.pop();
                }
                operators.pop();//poping ( symbol
            } else {
                while ((!operators.isEmpty()) && (!(operators.peek() == '(')) && (prec(symbol) <= (prec(operators.peek())))) {
                    postfix = postfix + operators.pop();
                }
                operators.push(symbol);
            }
     
        }
       while (!operators.isEmpty()) {
                postfix = postfix + operators.pop();
         }
       System.out.println(postfix);
            return postfix;
    }

    static int prec(char x) {
        if (x == '+' || x == '-') {
            return 1;
        }
        if (x == '*' || x == '/' || x == '%') {
            return 2;
        }
        return 0;
    }

}

class Stack {

    char a[] = new char[100];
    int maxsize = 50;
    int top = -1;

    public boolean isFull() {
        return (top >= (maxsize - 1));
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean push(char c) {
        if (isFull()) {
            System.out.println("Stack is full , size = " + maxsize);
            return false;
//            System.exit(0);
        } else {
            a[++top] = c;
            return true;
        }
    }

    char pop() {
        return a[top--];
    }

    char peek() {
        return a[top];
    }
}
