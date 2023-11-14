from django.urls import path
from .views import CommentListAPIView, CommentCreate, DeleteCommentView, CommentListByPost, DeleteCommentByPost
urlpatterns = [
   
    path("create", CommentCreate.as_view()),
    #path("get", CommentListAPIView.as_view()),
    path("<int:post_id>/get", CommentListByPost.as_view()),
    path("<int:pk>/delete",DeleteCommentView.as_view()),
    path("<int:post_id>/delpost", DeleteCommentByPost.as_view())
]