package com.application.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.blog.entities.Comment;
import com.application.blog.entities.Post;
import com.application.blog.exceptions.ResourceNotFoundException;
import com.application.blog.payloads.CommentDto;
import com.application.blog.repositories.CommentRepo;
import com.application.blog.repositories.PostRepo;
import com.application.blog.services.CommentService;

@Service
public class CommentServiceimpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo ;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	    Post post = this.postRepo.findById(postId)
	                    .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));	    
	    Comment comment = this.modelMapper.map(commentDto, Comment.class);	    
	    comment.setPost(post);	    
	    Comment savedComment = this.commentRepo.save(comment);	    
	    return this.modelMapper.map(savedComment, CommentDto.class);
	}


	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","CommentId",commentId));

	}

}
