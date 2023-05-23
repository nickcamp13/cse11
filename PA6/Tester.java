/*
 * Name:        Nicholas Campos
 * Email:       nicampos@ucsd.edu
 * PID:         A17621673
 * References:  JDK, Lecture notes, textbook
 *
 * This file tests the Post and User classes.
*/

/**
 * The Testser class performs tests on methods found in the Post and User
 * classes. 
 */
public class Tester {
    public static void main(String[] args) {
        User u1 = new User("CSE11Student");
        Post p1 = new Post("Title", "Content", u1);
        Post c1 = new Post("Comment", p1, u1);
        System.out.println(u1);
        System.out.println(p1);
        System.out.println(c1);
        u1.addPost(p1);
        u1.addPost(c1);
        System.out.println(u1.getTopPost());
        System.out.println(u1.getTopComment());
    }
}
