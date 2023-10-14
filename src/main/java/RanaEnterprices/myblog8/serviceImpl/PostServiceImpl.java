package RanaEnterprices.myblog8.serviceImpl;


import RanaEnterprices.myblog8.entity.Post;
import RanaEnterprices.myblog8.exception.ResourceNotFoundException;
import RanaEnterprices.myblog8.payload.PostDto;
import RanaEnterprices.myblog8.repository.PostRepository;
import RanaEnterprices.myblog8.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Instead of service layer they might call that as MODEL layer.
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepo, ModelMapper mapper) {
        this.postRepo = postRepo;
        this.mapper = mapper;
    }
// Instead @Autowired we can use this method to perform Dependency Injection.


//    @Override
//    public PostDto createPost(PostDto postDto) {
// here we are converting the DTO into ENTITY.
//        Post pst = new Post();

//        pst.setId(postDto.getId());
//        pst.setTitle(postDto.getTitle());
//        pst.setDescription(postDto.getDescription());
//        pst.setContent(postDto.getContent());

//        Post newpst = postRepo.save(pst);
    // here we saved the data and then load that data into {newpst}
//        PostDto dto = new PostDto();
// here we are converting Entity to DTO.
//        dto.setId(newpst.getId());
//        dto.setTitle(newpst.getTitle());
//        dto.setDescription(newpst.getDescription());
//        dto.setContent(newpst.getContent());

//        return dto;
    // We will call this from controller.//
//    }
//}

    // ANOTHER PROCESS OF DOING THE SAME THING

    // POSTDTO will go postman. and Service will go to database.
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        // mapToEntity will covert DTO to ENTITY.
        Post newpst = postRepo.save(post);

        PostDto newpostDto = mapToDto(newpst);

        return newpostDto;
    }

//    @Override
//    public List<PostDto> getAllPosts(int pageNo , int pageSize) {
//        List<Post> posts = postRepo.findAll();
//        return posts.stream().map(post ->mapToDto(post)).collect(Collectors.toList());
//    }



    //HOW TO GET ALL THE RECORDS ??????? DURING PAGINATION

    @Override
    public PostResponse getAllPosts(int pageNo , int pageSize , String sortBy , String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? // ternary operator
                //condition ? expression_if_true : expression_if_false // ternary operator
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo,pageSize,sort);
//
        Page<Post> content = postRepo.findAll(pageable);
        List<Post> posts = content.getContent();

        List<PostDto> dto = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(dto);
        postResponse.setPageNo(content.getNumber());
        postResponse.setPageSize(content.getSize());
        postResponse.setTotalPages(content.getTotalPages());
        postResponse.setTotalElements((int)content.getTotalElements());
        postResponse.setLast(content.isLast());

        return postResponse;
    }


    @Override
    public PostDto getPostByID(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post" , "Id" , id)
        );
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", id)
        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepo.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post" , "Id" , id)
        );
        postRepo.deleteById(id);
    }



    private Post mapToEntity(PostDto postDto){
        // we are making this method to use the converting process available for using anywhere.
//        Post pst = new Post();
// // here we are converting DTO to ENTITY.
//
//        pst.setId(postDto.getId());
//        pst.setTitle(postDto.getTitle());
//        pst.setDescription(postDto.getDescription());
//        pst.setContent(postDto.getContent());

        Post pst = mapper.map(postDto , Post.class);

        return pst;
    }

    private PostDto mapToDto(Post post){
// we are making this method to use the converting process available for using anywhere.
//        PostDto dto = new PostDto();
//// here we are converting Entity to DTO.
//
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());

        PostDto dto = mapper.map(post , PostDto.class);

        return dto;
    }


}