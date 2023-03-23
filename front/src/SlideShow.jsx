import React, { useState } from 'react';
import { Typeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import 'bootstrap/dist/css/bootstrap.css';

const SlideShow = ({ questions, nbQuestion }) => {
  console.log("questions", questions);
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
  const currentQuestionId = currentQuestionData.id;
  const currentQuestionText = currentQuestionData.contenu;
  const currentQuestionType = currentQuestionData.type;

  const options = [
    { nom: 'Alabama' },
    { nom: 'Alaska' },
    { nom: 'Arizona' },
    { nom: 'Arkansas' },
    { nom: 'California' }]
    ;

  function renderReponses( index){
    if (currentQuestionType === "TEXTE") {
      return (
        <input className='input-default' type="text" onChange={(event) => handleInputChange(index, event.target.value)} />
      );
    } else if (currentQuestionType === "LIST") {
      return (
        <Typeahead
          id={`typeahead-${index}`}
          multiple
          onChange={(selected) => handleInputChange(index, selected)}
          options={options}
          placeholder="Choisissez une ou plusieurs réponses..."
          selected={multiSelections[index]}
          labelKey="nom"
        />
      );
    }
  }

  return (
    <div className='slide-show'>
      <div className="progress" role="progressbar" aria-label="Example with label" aria-valuenow={currentQuestion} aria-valuemin="1" aria-valuemax={nbQuestion}>
        <div className="progress-bar" style={{ width: `${(currentQuestion + 1 / nbQuestion) * 100}%` }}><span>{currentQuestion + 1} / {nbQuestion}</span></div>
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