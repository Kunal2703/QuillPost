from rest_framework.serializers import ModelSerializer
from .models import Comment

class CommentSerializer(ModelSerializer):
    class Meta:
        model = Comment
        fields = ['comment_id', 'post_id', 'username', 'content', 'timestamp']