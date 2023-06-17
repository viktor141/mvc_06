package ru.viktor.model;

public class Post {
  private long id;
  private String content;

  private boolean removed;

  public Post() {
  }

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
    this.removed = false;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setRemoved(boolean removed) {
    this.removed = removed;
  }

  public boolean isRemoved() {
    return removed;
  }
}
