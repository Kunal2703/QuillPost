import axios from "axios";
import { getToken } from "../auth";

//for Auth part
export const BASE_URL_AUTH = 'http://localhost:8000';
export const myAuthAxios = axios.create({
    baseUrl: BASE_URL_AUTH,
});



//for Post part
export const BASE_URL_POST = 'http://localhost:8080';
export const myPostAxios = axios.create({
    baseURL: BASE_URL_POST,
});

export const myGetPostAxios = axios.create({
    baseURL: BASE_URL_POST,
})

myPostAxios.interceptors.request.use(config=>{
    const token=getToken()
    if(token){
        config.headers["jwt"]=""+token;
        return config
    }
} , error=> Promise.reject(error))



//for comment
export const BASE_URL_Comment = 'http://localhost:9000';
export const myCommentAxios = axios.create({
    baseURL: BASE_URL_Comment,
});

myCommentAxios.interceptors.request.use(config=>{
    const token=getToken()
    if(token){
        config.headers["jwt"]=""+token;
        return config
    }
} , error=> Promise.reject(error))