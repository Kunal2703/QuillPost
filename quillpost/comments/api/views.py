from django.shortcuts import render
from rest_framework import status
from rest_framework.views import APIView
from rest_framework.generics import ListAPIView, DestroyAPIView
from rest_framework.exceptions import AuthenticationFailed
from .models import Comment
from .serializers import CommentSerializer
import requests
from rest_framework.response import Response

# Create your views here.

class CommentCreate(APIView):
    def post(self, request):
        token = request.COOKIES.get('jwt')
        if not token:
            raise AuthenticationFailed("Unauthenticated")
        cookie_data={"jwt":token}
        response=requests.get("http://localhost:8000/api/user", cookies=cookie_data)
        if response.status_code == 200:
            username=response.json()["username"]
            data=request.data
            data["username"]=username
            serializer_class= CommentSerializer(data=data)
            serializer_class.is_valid(raise_exception=True)
            serializer_class.save()
            return Response(serializer_class.data)
        else:
            raise AuthenticationFailed("Token is invalid or expired")
       

class CommentListAPIView(ListAPIView):
    queryset = Comment.objects.all()
    serializer_class= CommentSerializer
    # def get_queryset(self):
    #     return super().get_queryset()

class CommentListByPost(ListAPIView):
    pass


class DeleteCommentView(DestroyAPIView):
    queryset=Comment.objects.all()
    serializer_class = CommentSerializer

    def destroy(self, request, *args, **kwargs):
        token=request.COOKIES.get('jwt')
        if not token:
            raise AuthenticationFailed("Unauthenticated")
        cookie_data={"jwt":token}
        response=requests.get("http://localhost:8000/api/user", cookies=cookie_data)
        if response.status_code == 200:
            try:
                instance = self.get_object()
                username=response.json()["username"]
                if instance.username==username:
                    self.perform_destroy(instance)
                    return Response({"detail": "Comment deleted successfully"}, status=status.HTTP_204_NO_CONTENT)
                else:
                    return Response({"detail": "Permission Denied"}, status=status.HTTP_403_FORBIDDEN)
            except Comment.DoesNotExist:
                return Response({"detail": "Comment not found"}, status=status.HTTP_404_NOT_FOUND)
            

class DeleteCommentByPost(DestroyAPIView):
    pass