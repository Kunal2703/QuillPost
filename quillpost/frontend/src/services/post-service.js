import { myPostAxios } from "./helper";
import { myCommentAxios } from "./helper";
import { myGetPostAxios } from "./helper";
import { BASE_URL_POST } from "./helper";
import { BASE_URL_Comment } from "./helper";

//create post function
export const createPost = (postData) => {

    //console.log(postData);
    
    return myPostAxios.post(`/category/${postData.categoryID}/posts`,postData)
        .then(response => response.data)

};

//get all posts
export const loadAllPosts = (pageNumber, pageSize) => {
    return myGetPostAxios.get(`/posts?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=date&sortDir=desc`)
        .then(response => response.data)
};


//load single post of given Id
export const loadPost = (postId) => {
    return myGetPostAxios.get("/posts/"+postId)
        .then((response) => response.data);
};

//for comment
export const createComment = (comment, postId) => {
    return myCommentAxios.post(`/${postId}/comments`, comment)
};