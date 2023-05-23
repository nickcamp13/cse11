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
     *
     * @param title - the title of an original post on reddit
     * @param content - the content of a reddit post
     * @param author
     */
    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        upvoteCount = 1;
        downvoteCount = 0;
    }

    /**
     *
     * @param content
     * @param replyTo
     * @param author
     */
    public Post(String content, Post replyTo, User author) {
        this.content = content;
        this.replyTo = replyTo;
        this.author = author;
        upvoteCount = 1;
        downvoteCount = 0;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    public Post getReplyTo() {
        return replyTo;
    }

    /**
     *
     * @return
     */
    public User getAuthor() {
        return author;
    }

    /**
     *
     * @return
     */
    public int getUpvoteCount() {
        return upvoteCount;
    }

    /**
     *
     * @return
     */
    public int getDownvoteCount() {
        return downvoteCount;
    }

    /**
     *
     * @param isIncrement
     */
    public void updateUpvoteCount(boolean isIncrement) {
        if (isIncrement) {
            upvoteCount++;
        } else {
            upvoteCount--;
        }
    }

    /**
     *
     * @param isIncrement
     */
    public void updateDownvoteCount(boolean isIncrement) {
        if (isIncrement) {
            downvoteCount++;
        } else {
            downvoteCount--;
        }
    }

    /**
     *
     * @return
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
     *
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