import React from 'react';
import { Link } from "react-router-dom";
class Sondages extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      cookies : props.cookies,
      sondageList : []
    };
  }

  componentDidMount() {
    const url = 'api/sondage/get-all';
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
    console.log(this.state.sondageList);
    return (
      this.state.sondageList.map((sondage,i) =>{
        return(
          <Link to={`/sondage/${sondage.id}`} key={i} state={{ nom: sondage.nom, aRepondu : sondage.aRepondu, nbQuestion : sondage.nbQuestion }}>
            <div className='sondage-div sondage-link'>
              <span className='sondage-div-name'>{sondage.nom}</span>
              <span className='sondage-div-nb-questions'>{sondage.nbQuestion} Questions</span>
            </div>
          </Link>
        )
      })
    )
  }
}
export default Sondages ;