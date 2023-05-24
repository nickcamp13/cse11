/*
 * Name:        Nicholas Campos
 * Email:       nicampos@ucsd.edu
 * PID:         A17621673
 * References:  JDK, Lecture notes, textbook
 *
 * This file implements the Post class. Posts are objects that belong
 * to unique Users. Other users can interact with an author's post by
 * upvoting or downvoting it.
*/

import java.util.ArrayList;

/**
 * The Post class holds data for an objects title, content, a reference to
 * potential original posts and author, and upvote/downvote counts. Posts can
 * either be original or a comment where the post is a reply to another post.
 * When a user interacts with a post by upvoting or downvoting this an object
 * of this class can update itself. Objects of this class can also return a
 * list of an objects thread if the Post object is a comment to another post.
 */
public class Post {
    private static final String TO_STRING_POST_FORMAT = "[%d|%d]\t%s\n\t%s";
    private static final String TO_STRING_COMMENT_FORMAT = "[%d|%d]\t%s";

    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;

    /**
     * Constructor for an ORIGINAL post (the first post in a thread)
     *
     * @param title   - the title of an original post on reddit
     * @param content - the content of a reddit post
     * @param author  - the name of the author of THIS post
     */
    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        upvoteCount = 1;
        downvoteCount = 0;
    }

    /**
     * Constructor for a REPLY post (post is not the first in its thread)
     *
     * @param content - the content of a reddit post
     * @param replyTo - the post that THIS post is in reply to
     * @param author  - the name of the author of THIS post
     */
    public Post(String content, Post replyTo, User author) {
        this.content = content;
        this.replyTo = replyTo;
        this.author = author;
        upvoteCount = 1;
        downvoteCount = 0;
    }

    /**
     * Returns the title of a post
     *
     * @return - the title of a post
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the reference to the post that THIS post is replying to
     *
     * @return the reference that THIS post is replying to
     */
    public Post getReplyTo() {
        return replyTo;
    }

    /**
     * Returns the name of the author of THIS post
     *
     * @return the name of the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Returns the number of upvotes on THIS post
     *
     * @return the number of upvotes
     */
    public int getUpvoteCount() {
        return upvoteCount;
    }

    /**
     * Returns the number of downvotes on THIS post
     *
     * @return the number of downvotes
     */
    public int getDownvoteCount() {
        return downvoteCount;
    }

    /**
     * Increments or decrements the number of upvotes depending on input
     *
     * @param isIncrement - if true upvoteCount incremented, upvoteCount is
     *                    decremented otherwise
     */
    public void updateUpvoteCount(boolean isIncrement) {
        if (isIncrement) {
            upvoteCount++;
        } else {
            upvoteCount--;
        }
    }

    /**
     * Increments/decrements the number of downvotes depending on input
     *
     * @param isIncrement - if true downvoteCount incremented, downvoteCount is
     *                    decremented otherwise
     */
    public void updateDownvoteCount(boolean isIncrement) {
        if (isIncrement) {
            downvoteCount++;
        } else {
            downvoteCount--;
        }
    }

    /**
     * Returns an ArrayList of the thread of a reply starting with the original
     * post and ending with THIS post
     *
     * @return the list of the thread
     */
    public ArrayList<Post> getThread() {
        ArrayList<Post> thread = new ArrayList<>();
        Post currentPost = this;
        while (currentPost != null) {
            thread.add(0, currentPost);
            currentPost = currentPost.getReplyTo();
        }
        return thread;
    }

    /**
     * Formats THIS Post object into readable text
     *
     * @return the formatted String of a Post object
     */
    public String toString() {
        if (replyTo == null) {
            return String.format(
                    TO_STRING_POST_FORMAT,
                    upvoteCount, downvoteCount,
                    title,
                    content);
        }
        return String.format(
                TO_STRING_COMMENT_FORMAT,
                upvoteCount, downvoteCount,
                content);
    }
}