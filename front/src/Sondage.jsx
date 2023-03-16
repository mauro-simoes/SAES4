import React, { useState } from 'react';
import withRouter from './withRouter';


class Sondage extends React.Component {
  constructor(props) {
    super(props);
    console.log(props);
    this.state = {
      nom : props.location.state.nom,
      aRepondu : props.location.state.aRepondu,
      nbQuestion : props.location.state.nbQuestion,

      id : props.params.id,
      cookies : props.cookies,
      token : props.cookies.token,
      questionsList : []
    };
  }

  componentDidMount() {
    // fetch questions of sondage
    const fullPath = window.location.href;
    let url = fullPath.replace('sondage','api/sondage/get-question-of-sondage')
    const token = this.state.token;

    fetch(url, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    })
    .then(response => response.json())
    .then(data => this.setState({ questionsList: data }))
    .catch(error => console.error(error));

    // checf if user respond to sondage
  }

  render() {
    return (
      <div>
        <h1>{this.state.nom}</h1>
        <h2>{this.state.nbQuestion} Questions</h2>
        <h2>{this.state.aRepondu ? 'Vous avez déjà répondu à ce sondage' : 'Vous n\'avez pas encore répondu à ce sondage'}</h2>
      </div>
    );
  }
  
}
export default withRouter(Sondage);