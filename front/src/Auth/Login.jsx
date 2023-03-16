import React, { useState } from 'react';

export const Login = (props) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const cookies = props.cookies;

  if(cookies.get('token')!=null){
    window.location.href = '/sondages';
  }
  else{
    const handleSubmit = async (e) => {
      e.preventDefault();
      try {
        const response = await fetch('/authentification/authentifier', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            "email" : email,
            "password" : password,
          })
        });

        const data = await response.json();
        if(typeof data.token === 'undefined'){
          console.log('Erreur : ', data.error);
          document.querySelector('.error-text').textContent = data.error;
          document.querySelector('.error').style.display = "flex";
        }
        else{
          cookies.set('token',data.token);
          window.location.href = '/';
        }
      } catch (error) {
        console.log(error);
      }
  };
  
    return (
      <div className="auth-form-container">
          <h2>Login</h2>
          <form className="login-form" onSubmit={handleSubmit}>
            <label htmlFor="email">Email:</label>
            <input type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)}/>
            <label htmlFor="password">Mot de passe:</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="********"
            />
          <button type="submit">Se connecter</button>
        </form>
        <button className="link-btn" onClick={() => props.onFormSwitch('register')}>Pas encore de compte ? Clique ici</button>
        <div className='error'>
          <span>Erreur</span>
          <span className='error-text'></span>
        </div>
      </div>
    );

  }
};

export default Login;