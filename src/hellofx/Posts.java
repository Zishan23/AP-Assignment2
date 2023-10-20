package hellofx;

import java.util.ArrayList;
import java.util.List;

public class Posts {
    private List<Post> posts; // A list to hold Post objects

    public Posts() {
        this.posts = new ArrayList<>(); // Initialize an empty list of posts
    }

    // Getters and setters for post attributes
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}

class Post {
    private int postId;
    private int userId;
    private String content;
    private int likes;
    private int shares;

    public Post(int postId, int userId, String content, int likes, int shares) {
        this.postId = postId;
      
        this.content = content;
        this.likes = likes;
        this.shares = shares;
    }

    // Getters and setters for post attributes
    public int getPostId() {
        return postId;
    }
    public int getUserId() {
        return userId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
