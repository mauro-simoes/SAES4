import React, { useState } from 'react';
import withRouter from './withRouter';
import SlideShow from './SlideShow';

class Sondage extends React.Component {
  constructor(props) {
    if(!props.cookies.token) {
      window.location.href = '/';
    }
    super(props);
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
    let url = 'http://localhost:8080/api/sondage/get-question-of-sondage/' + fullPath.split("/")[fullPath.split("/").length-1]
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
  }

  render() {
    return (
      <div className='sondage-container card'>
        <div className='sondage-header card-header'>
          <h1>{this.state.nom}</h1>
          <h2>{this.state.nbQuestion} Questions</h2>
        </div>
        {
          this.state.aRepondu ? <span> Vous avez déja répondu à ce sondage </span> : 

          this.state.questionsList.length === 0
            ? <div>Chargement...</div>
            : <SlideShow questions={this.state.questionsList} nbQuestion={this.state.nbQuestion} cookies={this.state.cookies} idSondage={this.state.id} />
        }
      </div>
    );
}
  
}
export default withRouter(Sondage);