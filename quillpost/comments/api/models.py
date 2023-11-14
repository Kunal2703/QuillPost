from django.db import models

# Create your models here.

class Comment(models.Model):
    comment_id= models.AutoField(primary_key=True);
    post_id= models.IntegerField();
    username= models.CharField(max_length=255)
    content= models.TextField(max_length=255)
    timestamp= models.DateTimeField(auto_now_add=True)