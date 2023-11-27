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
    def verifyPost(self,postId):
        response = requests.get(f"http://post:8080/api/posts/{postId}/find")
        if response.status_code==200:
            return True
        else:
            return False
        
    def post(self, request, postId):
        # token = request.COOKIES.get('jwt')
        token = request.headers['jwt']
        if not token:
            raise AuthenticationFailed("Unauthenticated")
        cookie_data={"jwt":token}
        response=requests.get("http://authentication:8000/api/user", cookies=cookie_data)
        
        if response.status_code == 200:
            
            if self.verifyPost(postId):
                username=response.json()["username"]
                data=request.data
                data["username"]=username
                data["post_id"]=postId
                serializer_class= CommentSerializer(data=data)
                serializer_class.is_valid(raise_exception=True)
                serializer_class.save()
                return Response(serializer_class.data, status=status.HTTP_201_CREATED)
            else:
                return Response({'error': 'Post does not exist.'}, status=status.HTTP_404_NOT_FOUND)
        else:
            raise AuthenticationFailed("Token is invalid or expired")
    

# class CommentListAPIView(ListAPIView):
#     queryset = Comment.objects.all()
#     serializer_class= CommentSerializer
#     def get_queryset(self):
#         return super().get_queryset()

class CommentListByPost(ListAPIView):
    serializer_class=CommentSerializer

    def get_queryset(self):
        post_id = self.kwargs.get('post_id')
        queryset=Comment.objects.filter(post_id=post_id)
        return queryset
    


class DeleteCommentView(DestroyAPIView):
    queryset=Comment.objects.all()
    serializer_class = CommentSerializer

    def destroy(self, request, *args, **kwargs):
        token=request.COOKIES.get('jwt')
        if not token:
            raise AuthenticationFailed("Unauthenticated")
        cookie_data={"jwt":token}
        response=requests.get("http://authentication:8000/api/user", cookies=cookie_data)

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
    serializer_class=CommentSerializer
    queryset=Comment.objects.all()

    def delete(self, request, *args, **kwargs):     
        post_id = self.kwargs.get('post_id')
        comments_to_delete = Comment.objects.filter(post_id=post_id)
        comments_to_delete.delete()
        return Response({"detail" : "Comments deleted successfully."}, status=status.HTTP_204_NO_CONTENT)
    