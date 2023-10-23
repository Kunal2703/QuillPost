import { myAxios } from "./helper";

export const SignUp = (user) => {
    return myAxios
        .post('/api/register', user)
        .then((response) => response.data)
};