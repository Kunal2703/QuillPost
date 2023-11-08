from django.urls import path
from .views import CommentListAPIView, CommentCreate, DeleteCommentView
urlpatterns = [
   
    path("create", CommentCreate.as_view()),
    path("get", CommentListAPIView.as_view()),
    path("<int:pk>/delete",DeleteCommentView.as_view())
]