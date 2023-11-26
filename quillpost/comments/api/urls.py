from django.urls import path
from .views import CommentCreate, DeleteCommentView, CommentListByPost, DeleteCommentByPost
urlpatterns = [
   
    path("post/<slug:postId>/comments", CommentCreate.as_view()),
    #path("get", CommentListAPIView.as_view()),
    path("post/<slug:post_id>/list/comments", CommentListByPost.as_view()),
    path("comments/<int:pk>",DeleteCommentView.as_view()),
    path("<int:post_id>/delpost", DeleteCommentByPost.as_view())
]