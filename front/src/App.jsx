import './css/colors.css';
import './css/App.css';
import './index.css';

import Register from './Auth/Register';
import Login from './Auth/Login';
import React, {useState} from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Sondages from './Sondages';
import Sondage from './Sondage';
import NotFound from './NotFound';
// import ReactDOM from 'react-dom';
import img from './img/logo.png';

import Cookies from 'universal-cookie';

// Use cookie to store JWT's login token

function App() {
  const [currentForm, setCurrentForm] = useState('login');
  const cookies = new Cookies();

  const toggleForm = (formName) =>{
    setCurrentForm(formName);
  }

  const [cookieState, setCookieState] = useState({
    token: cookies.get('token')
  });

  return (
    <div className="App">
      <header className='header-vos-sondages'>
          <div className='img-wrapper wrap-logo'>
            <img
          src={img}
          alt='logo'
          />
        </div>
        <h1>
          Vos sondages
        </h1>
        {/* <User name={'alban'}
        /> */}
        <button onClick={() =>{ cookies.remove('token'); window.location.href = '/'; }}> Se déconnecter </button>
      </header>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={currentForm === "login" ? <Login cookies={cookies} onFormSwitch={toggleForm} /> : <Register cookies={cookies} onFormSwitch={toggleForm} />}>
            </Route>
            <Route path="/sondages" element={<Sondages cookies={cookieState} />}>
            </Route>
            <Route path="/sondage/:id" element={<Sondage cookies={cookieState} />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </BrowserRouter>
      <footer>
        <span>Text Footer</span>
      </footer>
    </div>
  );

}

export default App;