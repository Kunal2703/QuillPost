import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import About from './pages/About';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Services from './pages/Services';

function App() {
  return (
    <BrowserRouter>
    <Routes>

      <Route path = "/" element = {<Home />} />
      <Route path = "/login" element = {<Login />} />
      <Route path = "/about" element = {<About />} />
      <Route path = "/signup" element = {<Signup />} />
      <Route path = "/services" element = {<Services />} />

      <Route path = "/about" element = {<About />} />

    </Routes>
    </BrowserRouter>
  );
}
export default App;
