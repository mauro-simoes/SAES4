import React, { useState } from 'react';

export const Register = (props) => {
  const [email, setEmail] = useState('');
  const [nom, setNom] = useState('');
  const [prenom, setPrenom] = useState('');
  const [password, setPassword] = useState('');
  const [ville, setVille] = useState('');
  const [dateNaissance, setDateNaissance] = useState('');

  const cookies = props.cookies;

  if(cookies.get('token')!=null){
    window.location.href = '/sondages';
  }
  else{
    const handleSubmit = async (e) => {
      e.preventDefault();
      try {
        const response = await fetch('http://localhost:8080/authentification/creer-compte', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            "email" : email,
            "nom" : nom,
            "prenom" : prenom,
            "password" : password,
            "ville" : ville,
            "dateNaissance" : dateNaissance
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
        console.error(error);
      }
    };

    return (
      <div className="auth-form-container">
        <h2>Inscription</h2>
      <form className='register-form' onSubmit={handleSubmit}>
        <label className='label-default' htmlFor="email">Email:</label>
        <input className='input-default'  type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)}/>
        <div className='register-row'>
            <label className='label-default' htmlFor="nom">Nom: </label>
            <input className='input-default'  value={nom} name="nom" onChange={(e) => setNom(e.target.value)} id="nom" placeholder="Nom" />
            <label className='label-default' htmlFor="prenom">Prénom:</label>
            <input className='input-default' type="text" id="prenom" value={prenom} onChange={(e) => setPrenom(e.target.value)}/>
        </div>
        <label className='label-default' htmlFor="password">Mot de passe:</label>
        <input className='input-default'  type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
        <div className='register-row'>
          <label className='label-default' htmlFor="ville">Ville:</label>
          <input className='input-default'  type="text" id="ville" value={ville} onChange={(e) => setVille(e.target.value)} />
          <label className='label-default' htmlFor="dateNaissance">Date de naissance:</label>
          <input className='input-default'  type="date" id="dateNaissance" value={dateNaissance} onChange={(e) => setDateNaissance(e.target.value)} />
        </div>
        <button className='btn btn-primary' type="submit">Créer un compte</button>
      </form>
        <button className="link-btn" onClick={() => props.onFormSwitch('login')}>Déja un compte ? Clique ici</button>
        <div className='error'>
            <span>Erreur</span>
            <span className='error-text'></span>
          </div>
      </div>
    );
  }
}

export default Register;