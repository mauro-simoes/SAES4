import React, { useState, useEffect } from 'react';
import { Typeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import 'bootstrap/dist/css/bootstrap.css';

const SlideShow = ({ questions, nbQuestion, cookies, idSondage }) => {
  const token = cookies.token;
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [responses, setResponses] = useState({});
  const [multiSelections, setMultiSelections] = useState([]);

  const handlePrev = () => {
    setCurrentQuestion(currentQuestion - 1);
  };

  const handleNext = () => {
    setCurrentQuestion(currentQuestion + 1);
  };

  const handleInputChange = (index, value) => {
    console.log(index, value, currentQuestionId)
    if (currentQuestionType === 'LIST') {
      let newValues = [];
      value.map((item) => newValues.push(String(item.id)));
      value = newValues;
    }
    else {
      value = [String(value)];
    }
    const newResponses = { ...responses };
    newResponses[String(index)] = value;
    setResponses(newResponses);
  };

  const handleSubmit = () => {
    const dataReponse = {
      idSondage: parseInt(idSondage),
      reponses: responses,
    };
    console.log(dataReponse);
    // fetch post request with dataReponse as body in JSON format to http://localhost:8080/api/repondre
    fetch('http://localhost:8080/api/sondage/repondre', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization' : `Bearer ${token}`
      },
      body: JSON.stringify(dataReponse),
      })
      .then((response) => response.json())
      .then((data) => {
        // redirect to sondages
        window.location.href = 'http://localhost:3000/sondages';
      }).then((error) => {
        console.log(error);
      }).catch((error) => {
        console.log(error);
      });
  };

  const currentQuestionData = questions[currentQuestion];
  const currentQuestionId = currentQuestionData.id;
  const currentQuestionText = currentQuestionData.contenu;
  const currentQuestionType = currentQuestionData.type;
  const currentQuestionNbReponseMin = currentQuestionData.nbReponseMin;
  const currentQuestionNbReponseMax = currentQuestionData.nbReponseMax;

  const fullPathReponse = window.location.href;
  
  const [repData, setRepData] = useState([]);
  const [repDataString, setRepDataString] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  
  async function fetchReponse() {
    setRepData([]);
    setRepDataString([]);
    setLoading(true);
    let urlReponse ='http://localhost:8080/api/reponse-question/'+(currentQuestion+1)
    const response = await fetch(urlReponse, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    const data = await response.json();
    setRepData(data);
    setLoading(false);
    setError(null);
    if (response.status !== 200) {
      setError(data);
      console.log(error);
    }
  }

  useEffect(() => {
    if (currentQuestionType === 'LIST' || currentQuestionType === "CHECKBOX") {
      fetchReponse();
    }
  }, [currentQuestion]);

  useEffect(() => {
    if ((currentQuestionType === 'LIST' || currentQuestionType === "CHECKBOX") && repData.length > 0) {
      repData.map((item) => ({
        id : item.id,
        reponse : item.reponse
      }));
    }
  }, [repData]);

  function renderReponses( index){
    if (currentQuestionType === "TEXTE") {
      return (
        <input className='input-default' type="text" onChange={(event) => handleInputChange(currentQuestionId, event.target.value)} />
      );
    } else if (currentQuestionType === "LIST") {
      return (
        <Typeahead
          id={`typeahead-${currentQuestionId}`}
          multiple
          onChange={(selected) => handleInputChange(currentQuestionId, selected)}
          options= {repData}
          placeholder="Choisissez une ou plusieurs réponses..."
          selected={multiSelections[currentQuestionId]}
          labelKey="reponse"
          filterBy={['reponse']}
        />
      );
    } else if (currentQuestionType === "CHECKBOX") {
      return (
        <div className='checkbox-container'>
          {
            repData.map((rep) => {
              return (
                <div className='form-check'>
                  {
                    (currentQuestionNbReponseMin === currentQuestionNbReponseMax) ?
                    (
                      <>
                        <input className='form-check-input' type="radio" id={rep.id} name={currentQuestion} value={rep.id} onChange={(event) => handleInputChange(currentQuestionId, event.target.value)} />
                        <label className='form-check-label' htmlFor={rep.id}>{rep.reponse}</label>
                      </>
                    )
                    :
                    (
                      <>
                        <input className='form-check-input' type="checkbox" id={rep.id} name={currentQuestion} value={rep.id} onChange={(event) => handleInputChange(currentQuestionId, event.target.value)} />
                        <label className='form-check-label' htmlFor={rep.id}>{rep.reponse}</label>
                      </>
                    )
                  }
                </div>
              );
            })
          }
        </div>
      );
    }
  }

  return (
    <div className='slide-show'>
      <div className="progress" role="progressbar" aria-label="Example with label" aria-valuenow={currentQuestion} aria-valuemin="1" aria-valuemax={nbQuestion}>
        <div className="progress-bar" style={{ width: `${(currentQuestion / nbQuestion) * 100}%` }}><span>{currentQuestion + 1} / {nbQuestion}</span></div>
      </div>
      <h2>{currentQuestionText}</h2>
      <div className='reponses-container'>
        {
          renderReponses(currentQuestion)
        }
      </div>
      <div className="btn-group p-3" role="group" aria-label="Basic example">
        <button type="button" className="btn btn-primary" onClick={handlePrev} disabled={currentQuestion === 0}><i class="fa-solid fa-arrow-left"></i> Précédent</button>
        <button type="button" className="btn btn-primary" onClick={handleNext} disabled={currentQuestion === (questions.length - 1)}>Suivant <i class="fa-solid fa-arrow-right"></i></button>
      </div>
      {currentQuestion === (questions.length - 1) && (
        <button onClick={handleSubmit} type="button" className="btn btn-success">
          <span className='submit-text'>Envoyer mes réponses </span>
          <i className="fa-solid fa-check"></i>
        </button>
      )}
    </div>
  );
};
export default SlideShow;