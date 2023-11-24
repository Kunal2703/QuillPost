//isLogedin => tokendetails
export const isLoggedIn = () => {
    let data = localStorage.getItem("data");
    if(data != null) {
        return true;
    }
    else {
        return false;
    }
};

//doLogin => data => set to localStorage
export const doLoggin = (data, next) => {
    localStorage.setItem("data", JSON.stringify(data));
    next()
};


//doLogout => remove from localStorage
export const doLogout = (next) => {
    localStorage.removeItem("data");
    next()
};

//get CurrentUser
export const getCurrentUserDetail = () => {
    if(isLoggedIn()){
        return JSON.parse(localStorage.getItem("data")).user;
    }
    else {
        return undefined;
    }
};

export const getToken=()=>{
    if(isLoggedIn()){
        return JSON.parse(localStorage.getItem("data")).jwt
    }else{
        return null;
    }
}