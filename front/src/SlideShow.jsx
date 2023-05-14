import React, { useState, useEffect } from 'react';
import { Typeahead } from 'react-bootstrap-typeahead';
import ReactLoading from 'react-loading';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import 'bootstrap/dist/css/bootstrap.css';

const SlideShow = ({ questions, nbQuestion, cookies, idSondage }) => {
  const token = cookies.token;
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [responses, setResponses] = useState({});
  const [multiReponse, setMultiReponse] = useState([]);

  const handlePrev = () => {
    setCurrentQuestion(currentQuestion - 1);
  };

  const handleNext = () => {
    setCurrentQuestion(currentQuestion + 1);
  };

  const handleInputChange = (index, value) => {
    setMultiReponse({ ...multiReponse, [index]: value });
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
    document.querySelector('.submit-text').textContent = 'Envoi en cours... ';
    document.querySelector('.loading-submit-icon').style.display = 'inline-block';
    document.querySelector('.fa-check').style.display = 'none';
    const dataReponse = {
      idSondage: parseInt(idSondage),
      reponses: responses,
    };
    console.log(dataReponse);
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
        setSubmitted(true);
      }).then((error) => {
        console.log(error);
      }).catch((error) => {
        console.log(error);
      });
  };

  const currentQuestionData = questions[currentQuestion] || {
    id: 0,
    contenu: '',
    type: '',
    nbReponseMin: 0,
    nbReponseMax: 0
  };

  const currentQuestionId = currentQuestionData.id;
  const currentQuestionText = currentQuestionData.contenu;
  const currentQuestionType = currentQuestionData.type;
  const currentQuestionNbReponseMin = currentQuestionData.nbReponseMin;
  const currentQuestionNbReponseMax = currentQuestionData.nbReponseMax;

  const fullPathReponse = window.location.href;
  
  const [repData, setRepData] = useState([]);
  const [repDataString, setRepDataString] = useState([]);
  const [loading, setLoading] = useState(false);
  const [submitted, setSubmitted] = useState(false);
  const [error, setError] = useState(null);
  
  async function fetchReponse() {
    setLoading(true);
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

  function renderReponses(){
    if (loading) {
      return (
        <ReactLoading type={'spin'} color={'#000000'}/>
      );
    } else if (error) {
      return (
        <div className='error-message'>
          <p>Une erreur est survenue lors du chargement des réponses.</p>
        </div>
      );
    } else {
      if (currentQuestionType === "TEXTE") {
        return (
          <input className='input-default' type="text" value={multiReponse[currentQuestionId] || ''} onChange={(event) => handleInputChange(currentQuestionId, event.target.value)} />
        );
      } else if (currentQuestionType === "LIST") {
        return (
          <Typeahead
            id={`typeahead-${currentQuestionId}`}
            multiple
            onChange={(selected) => handleInputChange(currentQuestionId, selected)}
            options= {repData}
            placeholder="Choisissez une ou plusieurs réponses..."
            labelKey="reponse"
            filterBy={['reponse']}
            selected={multiReponse[currentQuestionId] || []}
          />
        );
      } else if (currentQuestionType === "CHECKBOX") {
        // si currentQuestionNbReponseMin === currentQuestionNbReponseMax alors on affiche des radio sinon checkbox
        let type = (currentQuestionNbReponseMin === currentQuestionNbReponseMax) ? 'radio' : 'checkbox';
        return (
          <div className='checkbox-container'>
            {
              repData.map((rep) => {
                return (
                  <div className='form-check'>
                    {
                      // si y a deja des rep dans multiReponse et que la rep actuelle est dedans
                      (multiReponse[currentQuestionId] && multiReponse[currentQuestionId].includes(String(rep.id))) ?
                      (
                        <>
                          <input className='form-check-input' type={type} id={rep.id} name={currentQuestion} value={rep.id} checked onChange={(event) => handleInputChange(currentQuestionId, event.target.value)} />
                          <label className='form-check-label' htmlFor={rep.id}>{rep.reponse}</label>
                        </>
                      )
                      :
                      (
                        <>
                          <input className='form-check-input' type={type} id={rep.id} name={currentQuestion} value={rep.id} onChange={(event) => handleInputChange(currentQuestionId, event.target.value)} />
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
  }

  return (
  <div className='slide-show'>
    <div className="progress" role="progressbar" aria-label="Example with label" aria-valuenow={currentQuestion} aria-valuemin={0} aria-valuemax={nbQuestion}>
      <div className="progress-bar" style={{ width: `${(currentQuestion / (nbQuestion-1)) * 100}%` }}><span>{currentQuestion + 1} / {nbQuestion + 1}</span></div>
    </div>
    {submitted ? (
      <div className='submitted-response-div'>
        <h3>Vos réponses ont bien été envoyées. <i class="fa-solid repondu-true fa-circle-check"></i></h3>
      </div>
    ) : (
      <>
        {currentQuestion !== questions.length && (
          <div className='question-container'>
            <h2 className='question-text'>{currentQuestionText}</h2>
            <div className='question-input-container'>
              {renderReponses()}
            </div>
          </div>
        )}
        {currentQuestion === questions.length && (
          <div>
            <h2>Souhaitez-vous envoyer vos réponses ?</h2>
          </div>
        )}
        <div className="btn-group p-3" role="group" aria-label="Basic example">
          <button type="button" className="btn btn-primary" onClick={handlePrev} disabled={currentQuestion === 0}><i className="fa-solid fa-arrow-left"></i> Précédent</button>
          <button type="button" className="btn btn-primary" onClick={handleNext} disabled={currentQuestion === questions.length}><i className="fa-solid fa-arrow-right"></i> Suivant</button>
        </div>
        {currentQuestion === questions.length && (
          <button onClick={handleSubmit} type="button" className="btn btn-success">
            <span className='submit-text'> Envoyer mes réponses </span>
            <i className="fa-solid fa-check"></i>
            <i style={{ display: "none" }} className="fas loading-submit-icon fa-circle-notch fa-spin"></i>
          </button>
        )}
      </>
    )}
  </div>
);

  
};
export default SlideShow;