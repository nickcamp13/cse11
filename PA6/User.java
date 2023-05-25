/*
 * Name:        Nicholas Campos
 * Email:       nicampos@ucsd.edu
 * PID:         A17621673
 * References:  JDK, Lecture notes, textbook
 *
 * This file implements the User class. Users are objects that can create
 * posts that other Users can interact with. User objects maintain data
 * such as karma and lists of their own posts and posts that they have
 * upvoted or downvoted.
*/

import java.util.ArrayList;

/**
 * The User class emulates a user account on a social media platform similar to
 * reddit by holding information on a User's username, karma, and lists for
 * their posts, the posts that they have upvoted, and posts they have
 * downvoted. Users can add posts to their account, interact with other User's
 * posts by downvoting or upvoting, and find their top original post or top
 * comment.
 */
public class User {
    private static final String TO_STRING_USER_FORMAT = "u/%s Karma: %d";

    private String username;
    private int karma;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;

    /**
     * Constructor to initialize instance variables
     *
     * @param username - the alias of a User
     */
    public User(String username) {
        this.username = username;
        karma = 0;
        posts = new ArrayList<>();
        upvoted = new ArrayList<>();
        downvoted = new ArrayList<>();
    }

    /**
     * Adds a post to a User's list of posts
     *
     * @param post - the Post to be added to a User's account
     */
    public void addPost(Post post) {
        if (post == null) {
            return;
        }
        posts.add(post);
        updateKarma();
    }

    /**
     * Calculates karma for each Post and sums the value (upvotes - downvotes)
     */
    public void updateKarma() {
        karma = 0;
        for (Post post : posts) {
            int net_karma = post.getUpvoteCount() - post.getDownvoteCount();
            karma += net_karma;
        }
    }

    /**
     * Returns the User's current karma
     *
     * @return - the net value of a users upvotes and downvotes from all of
     *         their posts
     */
    public int getKarma() {
        return karma;
    }

    /**
     * Enables THIS User to upvote ANOTHER User's Post
     *
     * @param post - the Post of ANOTHER User that THIS User wants to upvote
     */
    public void upvote(Post post) {
        if (post == null) {
            return;
        }
        if (upvoted.contains(post) || post.getAuthor() == this) {
            return;
        }
        if (downvoted.contains(post)) {
            downvoted.remove(post);
            post.updateDownvoteCount(true);
        }
        upvoted.add(post);
        post.updateUpvoteCount(true);
        post.getAuthor().updateKarma();
    }

    /**
     * Enables THIS User to downvote another User's Post
     *
     * @param post - the Post of ANOTHER User that THIS User wants to downvote
     */
    public void downvote(Post post) {
        if (post == null) {
            return;
        }
        if (downvoted.contains(post) || post.getAuthor() == this) {
            return;
        }
        if (upvoted.contains(post)) {
            upvoted.remove(post);
            post.updateUpvoteCount(true);
        }
        downvoted.add(post);
        post.updateDownvoteCount(true);
        post.getAuthor().updateKarma();
    }

    /**
     * Returns the User's highest rated original Post
     *
     * @return - an ORIGINAL Post of THIS User that is the highest net value of
     *         upvotes if two of THIS User's Posts have the same karma the
     *         first one in the list will be returned
     */
    public Post getTopPost() {
        Post topPost = null;
        int count = 0;
        for (Post post : posts) {
            if (post.getReplyTo() == null) {
                topPost = post;
                break;
            }
            count++;
        }
        if (topPost == null) {
            return null;
        }
        for (int i = count; i < posts.size(); ++i) {
            Post currentPost = posts.get(i);
            if (currentPost.getReplyTo() != null) {
                continue;
            }
            int netCurrentPost = currentPost.getUpvoteCount()
                    - currentPost.getDownvoteCount();
            int netTopPost = topPost.getUpvoteCount()
                    - topPost.getDownvoteCount();
            if (netCurrentPost > netTopPost) {
                topPost = currentPost;
            }
        }
        return topPost;
    }

    /**
     * Returns the User's highest rated comment Post
     *
     * @return - a Comment Post of THIS User that is the highest net value of
     *         upvotes if two of THIS User's Posts have the same karma the
     *         first one in the list will be returned
     */
    public Post getTopComment() {
        Post topComment = null;
        int count = 0;
        for (Post post : posts) {
            if (post.getReplyTo() != null) {
                topComment = post;
                break;
            }
            count++;
        }
        if (topComment == null) {
            return null;
        }
        for (int i = count; i < posts.size(); ++i) {
            Post currentPost = posts.get(i);
            if (currentPost.getReplyTo() != null) {
                continue;
            }
            int netCurrentPost = currentPost.getUpvoteCount()
                    - currentPost.getDownvoteCount();
            int netTopPost = topComment.getUpvoteCount()
                    - topComment.getDownvoteCount();
            if (netCurrentPost > netTopPost) {
                topComment = currentPost;
            }
        }
        return topComment;
    }

    /**
     * Returns the list of a User's Post
     *
     * @return - the list of THIS User's Posts (Originals and Comments)
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Formats THIS User object into readable text
     */
    public String toString() {
        return String.format(TO_STRING_USER_FORMAT, username, karma);
    }
}