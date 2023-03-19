import React, { useState } from 'react';
import { Typeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import 'bootstrap/dist/css/bootstrap.css';

const SlideShow = ({ questions, nbQuestion }) => {
  console.log(nbQuestion);
  const [currentQuestion, setCurrentQuestion] = useState(1);
  const [responses, setResponses] = useState({});
  const [multiSelections, setMultiSelections] = useState([]);

  const handlePrev = () => {
    setCurrentQuestion(currentQuestion - 1);
  };

  const handleNext = () => {
    setCurrentQuestion(currentQuestion + 1);
  };

  const handleInputChange = (index, value) => {
    const newResponses = { ...responses };
    newResponses[index] = value;
    setResponses(newResponses);
  };

  const handleSubmit = () => {
    // Soumettre les réponses
    console.log(responses);
    console.log(multiSelections);
  };

  const currentQuestionData = questions[currentQuestion];
  const questionText = currentQuestionData[0];

  const options = [
    { nom: 'Alabama' },
    { nom: 'Alaska' },
    { nom: 'Arizona' },
    { nom: 'Arkansas' },
    { nom: 'California' }]
    ;

  function renderReponses(question, index){
    if (question[1] === "TEXTE") {
      return (
        <input className='input-default' type="text" onChange={(event) => handleInputChange(index, event.target.value)} />
      );
    } else if (question[1] === "LIST") {
      return (
        <Typeahead
          id={`typeahead-${index}`}
          multiple
          onChange={setMultiSelections}
          options={options}
          placeholder="Choisissez une ou plusieurs réponses..."
          selected={multiSelections}
          labelKey="nom"
        />

      );
    }
  }

  return (
    <div className='slide-show'>
      <div className="progress" role="progressbar" aria-label="Example with label" aria-valuenow={currentQuestion} aria-valuemin="0" aria-valuemax={nbQuestion}>
        <div className="progress-bar" style={{ width: `${(currentQuestion / nbQuestion) * 100}%` }}><span>{currentQuestion} / {nbQuestion}</span></div>
      </div>
      <h2>{questionText}</h2>
      <div className='reponses-container'>
        {
          renderReponses(currentQuestionData, currentQuestion)
        }
      </div>
      <div className="btn-group p-3" role="group" aria-label="Basic example">
        <button type="button" className="btn btn-primary" onClick={handlePrev} disabled={currentQuestion === 1}><i class="fa-solid fa-arrow-left"></i> Précédent</button>
        <button type="button" className="btn btn-primary" onClick={handleNext} disabled={currentQuestion === Object.keys(questions).length}>Suivant <i class="fa-solid fa-arrow-right"></i></button>
      </div>
      {currentQuestion === Object.keys(questions).length && (
        <button onClick={handleSubmit} type="button" className="btn btn-success">
          <span className='submit-text'>Envoyer mes réponses </span>
          <i className="fa-solid fa-check"></i>
        </button>
      )}
    </div>
  );
};
export default SlideShow;