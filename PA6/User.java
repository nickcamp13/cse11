import java.util.ArrayList;

public class User {
    // the username for this User
    private String username;

    // user's score of reputation
    // calculated by: (total # upvotes - total # downvotes)
    private int karma;

    // List of posts this User has made (includes both OG and comments)
    private ArrayList<Post> posts;

    // List of other User's Posts that this User upvoted
    private ArrayList<Post> upvoted;

    // List of other User's Posts that this User downvoted
    private ArrayList<Post> downvoted;
}