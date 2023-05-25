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

    /**
     * Used for testing objects from the Post and User class.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        User u1 = new User("nickcamp13 on Github");
        User u2 = new User("L1nearDev on Twitter");
        User u3 = new User("nicholas-campos on Linkedin");

        User onlyOriginals = new User("originalGuy");
        User onlyComments = new User("TheReplier");

        onlyOriginals.addPost(new Post(
                "Which class should I take?",
                "please help",
                onlyOriginals));
        onlyOriginals.addPost(new Post(
                "Need new ideas",
                "I am not creative",
                onlyOriginals));

        onlyComments.addPost(new Post(
                "take cse 11",
                onlyOriginals.getPosts().get(0),
                onlyComments));
        onlyComments.addPost(new Post(
                "same",
                onlyOriginals.getPosts().get(1),
                onlyComments));

        Post o1 = new Post(
                "I like reddit!",
                "It is so cool, my favorite subreddit is r/CSMajors",
                u1);
        u1.addPost(o1);
        Post o2 = new Post(
                "I do not like reddit.",
                "Twitter is my favorite",
                u2);
        u2.addPost(o2);
        Post o3 = new Post(
                "What is reddit?",
                "I have never heard of this place",
                u3);
        u3.addPost(o3);

        Post cU1_1 = new Post(
                "How could you not like reddit???",
                o2,
                u1);
        u1.addPost(cU1_1);
        Post cU2_1 = new Post("Follow me on Twitter", o3, u2);
        u2.addPost(cU2_1);
        Post cU3_1 = new Post(
                "Why do you like reddit? Should I make an account?",
                o1,
                u3);
        u3.addPost(cU3_1);

        Post pCu1_2 = new Post("Give it a try!", cU2_1, u1);
        u1.addPost(pCu1_2);
        Post pCu2_2 = new Post("Ew", cU3_1, u2);
        u2.addPost(pCu2_2);
        Post pCu3_2 = new Post("Hello, World", cU1_1, u3);
        u3.addPost(pCu3_2);

        u1.downvote(o2);
        u1.upvote(o3);
        u1.upvote(cU3_1);
        u1.downvote(pCu2_2);
        u1.upvote(pCu3_2);

        u2.downvote(o1);
        u2.upvote(o3);
        u2.downvote(cU3_1);
        u2.downvote(pCu1_2);
        u2.upvote(pCu3_2);

        u3.upvote(cU2_1);
        u3.upvote(pCu1_2);
        u3.downvote(pCu2_2);

        System.out.println(
                "Thread of user 1's second comment:\n" + pCu1_2.getThread());
        System.out.println(
                "Thread of user 2's second comment:\n" + pCu2_2.getThread());
        System.out.println(
                "Thread of user 3's second comment:\n" + pCu3_2.getThread());

        System.out.println(
                "User 1's top original post:\n" + u1.getTopPost());
        System.out.println(
                "User 1's top comment:\n" + u1.getTopComment());
        System.out.println(
                "User 2's top original post:\n" + u2.getTopPost());
        System.out.println(
                "User 2's top comment:\n" + u2.getTopComment());
        System.out.println(
                "User 3's top original post:\n" + u3.getTopPost());
        System.out.println(
                "User 3's top comment:\n" + u3.getTopComment());

        Post dummyPost = null;
        u1.addPost(dummyPost);

        o1.updateUpvoteCount(true);
        o1.updateUpvoteCount(false);

        o2.updateDownvoteCount(true);
        o2.updateDownvoteCount(false);

        System.out.println("User 1's karma: " + u1.getKarma());
        System.out.println("User 2's karma: " + u2.getKarma());
        System.out.println("User 3's karma: " + u3.getKarma());

        System.out.println(onlyOriginals.getTopComment());
        System.out.println(onlyComments.getTopPost());

    }
}
