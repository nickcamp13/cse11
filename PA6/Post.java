import java.util.ArrayList;

public class Post {

    // Title of a reddit post
    // if this Post is comment, should be NULL
    // if this Post is an original post, should be NON-NULL
    private String title;

    // Content of a reddit post
    // if this post is a comment, content is the comment a User made
    private String content;

    // The original Post this comment is replying to
    // if this Post is original, should be NULL
    // if this Post is a comment, should be NON-NULL
    private Post replyTo;

    // The thread of this Post until the original Post
    // The list should begin with the original Post and end with this Post
    // if this is the original Post, it should be the only one in the list
    // private ArrayList<Post> thread;

    // Author of this Post
    private User author;

    // # of upvotes for this Post
    private int upvoteCount;

    // # of downvotes for this Post
    private int downvoteCount;

    // ORIGINAL POST constructor
    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        // thread = new ArrayList<>();
        // thread.add(this);
        upvoteCount = 1;
        downvoteCount = 0;

        // What about replyTo?
    }

    // COMMENT constructor
    public Post(String content, Post replyTo, User author, String title) {
        this.content = content;
        this.replyTo = replyTo;
        this.author = author;
        this.title = title; // remove (this is for testing)
        // thread = new ArrayList<>();
        // setThread(this);
        upvoteCount = 1;
        downvoteCount = 0;
    }

    public String getTitle() {
        return title;
    }

    public Post getReplyTo() {
        return replyTo;
    }

    public User getAuthor() {
        return author;
    }

    public int getUpvoteCount() {
        return upvoteCount;
    }

    public int getDownvoteCount() {
        return downvoteCount;
    }

    public void updateUpvoteCount(boolean isIncrement) {
        if (isIncrement) {
            upvoteCount++;
        } else {
            upvoteCount--;
        }
    }

    public void updateDownvoteCount(boolean isIncrement) {
        if (isIncrement) {
            downvoteCount++;
        } else {
            downvoteCount--;
        }
    }

    public ArrayList<Post> getThread() {
        ArrayList<Post> thread = new ArrayList<>();
        Post currenPost = this;
        thread.add(currenPost);
        while (currenPost.getReplyTo() != null) {
            thread.add(currenPost.getReplyTo());
        }
    }

    public void printThread() {
        for (Post p : getThread()) {
            System.out.println(p.title);
        }
    }
}