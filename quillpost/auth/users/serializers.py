from rest_framework import serializers
from .models import User, UserProfile

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'username', 'first_name', 'last_name', 'email', 'password']
        extra_kwargs = {
            'password' : {'write_only' : True}
        }

    def create(self, validated_data):            #overriding create function to hash the password
        password = validated_data.pop('password', None) 
        instance = self.Meta.model(**validated_data)
        if password is not None:                       
            instance.set_password(password)
        instance.save()
        return instance
    
class UserProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserProfile
        fields = ['user_id', 'profile_pic', 'bio']