package com.primarchan.sns.controller;

import com.primarchan.sns.controller.request.PostCommentsRequest;
import com.primarchan.sns.controller.request.PostCreateRequest;
import com.primarchan.sns.controller.request.PostModifyRequest;
import com.primarchan.sns.controller.response.CommentResponse;
import com.primarchan.sns.controller.response.PostResponse;
import com.primarchan.sns.controller.response.Response;
import com.primarchan.sns.model.Post;
import com.primarchan.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;

    /**
     * @apiNote 포스트 작성 API
     * @param request
     * @param authentication
     * @return
     */
    @PostMapping
    public Response<Void> create(@RequestBody PostCreateRequest request, Authentication authentication) {
        postService.create(request.getTitle(), request.getBody(), authentication.getName());
        return Response.success();
    }


    /**
     * @apiNote 포스트 수정 API
     * @param postId
     * @param request
     * @param authentication
     * @return
     */
    @PutMapping("/{postId}")
    public Response<PostResponse> modify(@PathVariable Integer postId, @RequestBody PostModifyRequest request, Authentication authentication) {
        Post post = postService.modify(request.getTitle(), request.getBody(), authentication.getName(), postId);
        return Response.success(PostResponse.fromPost(post));
    }

    /**
     * @apiNote 포스트 삭제 API
     * @param postId
     * @param authentication
     * @return
     */
    @DeleteMapping("/{postId}")
    public Response<Void> delete(@PathVariable Integer postId, Authentication authentication) {
        postService.delete(authentication.getName(), postId);
        return Response.success();
    }

    /**
     * @apiNote 피드 목록 조회 API
     * @param pageable
     * @param authentication
     * @return
     */
    @GetMapping
    public Response<Page<PostResponse>> list(Pageable pageable, Authentication authentication) {
        return Response.success(postService.list(pageable).map(PostResponse::fromPost));
    }

    /**
     * @apiNote 내 피드 목록 조회 API
     * @param pageable
     * @param authentication
     * @return
     */
    @GetMapping("/my")
    public Response<Page<PostResponse>> my(Pageable pageable, Authentication authentication) {
        return Response.success(postService.my(authentication.getName(), pageable).map(PostResponse::fromPost));
    }

    /**
     * @apiNote 좋아요 기능 API
     * @param postId
     * @param authentication
     * @return
     */
    @PostMapping("/{postId}/likes")
    public Response<Void> like(@PathVariable Integer postId, Authentication authentication) {
        postService.like(postId, authentication.getName());
        return Response.success();
    }

    @GetMapping("{postId}/likes")
    public Response<Long> likeCount(@PathVariable Integer postId, Authentication authentication) {
        return Response.success(postService.likeCount(postId));
    }

    @PostMapping("/{postId}/comments")
    public Response<Void> comment(
            @PathVariable Integer postId,
            @RequestBody PostCommentsRequest request,
            Authentication authentication)
    {
        postService.comment(postId, authentication.getName(), request.getComment());
        return Response.success();
    }

    @GetMapping("/{postId}/comments")
    public Response<Page<CommentResponse>> comment(@PathVariable Integer postId, Pageable pageable) {
        return Response.success(postService.getComments(postId, pageable).map(CommentResponse::fromComment));
    }

}
