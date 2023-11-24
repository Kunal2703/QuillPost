from django.urls import path, include
from .views import RegisterView, LoginView, UserView, LogoutView, UserProfileView
urlpatterns = [
   
    path("register", RegisterView.as_view()),
    path("login", LoginView.as_view()),
    path("user", UserView.as_view()),
    path("logout", LogoutView.as_view()),
    path('user-profile/<int:pk>', UserProfileView.as_view()),
    path("user-profile/create", UserProfileView.as_view())

]
