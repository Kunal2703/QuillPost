import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import About from './pages/About';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Services from './pages/Services';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify'
import UserDashboard from './pages/user-routes/UserDashboard';
import PrivateRoute from './components/PrivateRoute';
import ProfileInfo from './pages/user-routes/ProfileInfo';

function App() {
  return (
    <BrowserRouter>
    < ToastContainer />
    <Routes>

      <Route path = "/" element = {<Home />} />
      <Route path = "/login" element = {<Login />} />
      <Route path = "/about" element = {<About />} />
      <Route path = "/signup" element = {<Signup />} />
      <Route path = "/services" element = {<Services />} />
      <Route path = "/about" element = {<About />} />

      <Route path = "/user" element = {<PrivateRoute />} > 
        <Route path = "dashboard" element = {<UserDashboard />} />
        <Route path = "profile-info" element = {<ProfileInfo />} />

      </Route>
    </Routes>
    </BrowserRouter>
  );
}
export default App;
