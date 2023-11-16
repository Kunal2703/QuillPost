//isLogedin => tokendetails
export const isLoggedIn = () => {
    let data = localStorage.getItem("data")
    if(data == null) {
        return false;
    }
    else {
        return true;
    }
};

//doLogin => data => set to localStorage
export const doLoggin = (data, next) => {
    localStorage.setItem("data", JSON.stringify(data));
    next();
};


//doLogout => remove from localStorage
export const doLogout = (next) => {
    localStorage.removeItem("data");
    next();
};

//get CurrentUser
export const getCurrentUserDetail = () => {
    if(isLoggedIn) {
        return JSON.parse(localStorage.getItem("data"));
    }
    else {
        return false;
    }
};