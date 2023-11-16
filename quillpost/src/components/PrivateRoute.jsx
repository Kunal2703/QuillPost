import React from 'react'
import { Outlet, Navigate} from 'react-router-dom'
import { isLoggedIn } from '../auth';


const  PrivateRoute = () => {

    return isLoggedIn() ? <Outlet /> : <Navigate to = {"/login"} />

}
export default PrivateRoute