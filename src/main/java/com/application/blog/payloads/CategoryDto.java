package com.application.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import com.application.blog.entities.Post;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min=4,message ="MIn size of category in 4")	
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10,message ="MIn size of category dexcription is 10")
	private String categoryDescription;
	
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> post= new ArrayList<>();
}






