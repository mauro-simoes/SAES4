import React from 'react';
import { Link } from "react-router-dom";
import ReactLoading from 'react-loading';

class Sondages extends React.Component {
  constructor(props) {
    if(!props.cookies.token) {
      window.location.href = '/';
    }
    super(props);
    this.state = {
      cookies : props.cookies,
      sondageList : []
    };
  }

  componentDidMount() {
    const url = 'http://localhost:8080/api/sondage/get-all';
    const token = this.state.cookies.token;

    fetch(url, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    })
    .then(response => response.json())
    .then(data =>this.setState({ sondageList: data }))
    .catch(error => console.error(error));
  }

  render() {
    return (
      this.state.sondageList.map((sondage, i) => {
        return (
            sondage.aRepondu ? (
              <>
              <div className='sondage-link disabled-link'>
                <div className='sondage-div'>
                  <span className='sondage-div-name'>{sondage.nom}</span>
                  <span className='sondage-div-nb-questions'>{sondage.nbQuestion} Questions</span>
                </div>
                <i title='Vous avez déjà répondu à ce sondage' className="fa-solid info-repondu repondu-true fa-check-circle"></i>
              </div>
              </>
            ) : (
              <Link to={`/sondage/${sondage.id}`} key={i} 
          state={{ nom: sondage.nom, aRepondu : sondage.aRepondu, nbQuestion : sondage.nbQuestion }}>
                <div className='sondage-link'>
                  <div className='sondage-div'>
                    <span className='sondage-div-name'>{sondage.nom}</span>
                    <span className='sondage-div-nb-questions'>{sondage.nbQuestion} Questions</span>
                  </div>
                  <i title="Vous n'avez pas encore répondu à ce sondage" className="fa-solid info-repondu repondu-false fa-circle-question"></i>
                </div>
              </Link>
            )
        );
      })
    );
  }
    
}
export default Sondages ;