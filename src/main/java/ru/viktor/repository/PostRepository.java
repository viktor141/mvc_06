package ru.viktor.repository;

import org.springframework.stereotype.Repository;
import ru.viktor.exception.NotFoundException;
import ru.viktor.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
@Repository
public class PostRepository {

  private final AtomicLong id = new AtomicLong(0);
  private final ConcurrentHashMap<Long, Post> postMap = new ConcurrentHashMap<>();


  public List<Post> all() {
    return postMap.values().stream().filter(post -> !post.isRemoved()).collect(Collectors.toList());
  }

  public Optional<Post> getById(long id) {
    Post post = postMap.get(id);
    if(post != null && !post.isRemoved()){
      return Optional.of(post);
    }
    return Optional.empty();
  }

  public Post save(Post post) {
    if(post.getId() == 0){
      post.setId(id.addAndGet(1));
      postMap.put(post.getId(), post);
      return post;
    }

    if(!postMap.containsKey(post.getId()))throw new NotFoundException();

    Post editedPost = postMap.get(post.getId());

    if(editedPost.isRemoved())throw new NotFoundException();


    editedPost.setContent(post.getContent());

    return post;
  }

  public void removeById(long id) {
    if(postMap.containsKey(id)){
      postMap.get(id).setRemoved(true);
    }
  }
}
