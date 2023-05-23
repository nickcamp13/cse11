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
     *
     * @param username
     */
    public User(String username) {
        this.username = username;
        karma = 0;
        posts = new ArrayList<>();
        upvoted = new ArrayList<>();
        downvoted = new ArrayList<>();
    }

    /**
     *
     * @param post
     */
    public void addPost(Post post) {
        if (post == null) {
            return;
        }
        posts.add(post);
        updateKarma();
    }

    /**
     *
     */
    public void updateKarma() {
        karma = 0;
        for (Post post : posts) {
            int net_karma = post.getUpvoteCount() - post.getDownvoteCount();
            karma += net_karma;
        }
    }

    /**
     *
     * @return
     */
    public int getKarma() {
        return karma;
    }

    /**
     *
     * @param post
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
     *
     * @param post
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
     *
     * @return
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
     *
     * @return
     */
    public Post getTopComment() {
        Post topComment = null;
        int count = 0;
        for (Post post : posts) {
            if (post.getReplyTo() != null) {
                topComment = post;
                break;
            }
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
     *
     * @return
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * 
     */
    public String toString() {
        return String.format(TO_STRING_USER_FORMAT, username, karma);
    }
}