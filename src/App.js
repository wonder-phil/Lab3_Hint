import logo from './logo.svg';
import './App.css';

import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';



var db_answer = "";
var id = "";

var list_of_bookmarked_ids =[];

function validate_answer(db_answer) {
	var user_answer = document.getElementById("answer_box").value;
	console.log(user_answer);
	console.log(db_answer);
	if (db_answer == user_answer) {
			alert("Yes!");
		} else {
			alert("No...: " + db_answer);
		}
}

async function getData(bookmark) {
    
    fetch('http://127.0.0.1:5000/http://localhost:8182/GET').then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data);
        document.getElementById("question-box").innerHTML = data["question"];
		db_answer = data["answer"];
		id = String(data["id"]);
		if (bookmark) {
			list_of_bookmarked_ids.push(data["id"]);
		}
		//alert("id: " + id);
    }).catch(function (e) {
        console.log(e);
    });
}

async function bookmarkQuestion() {
	getData(true);

}


function done() {
	console.log("List of bookmarked questions: ", list_of_bookmarked_ids);
}



function App() {
  
  const [answer, setAnswer] = useState("")
  
  const handleChange = e => {
	setAnswer(e.target.value)
  }

  return (
	
    <div className="App">
      <header className="App-header">
		  <p></p>
		  <div id="question-box"></div>
		  <br />
		  <div id="answer-box"></div>
		  <input id="answer_box" type="text" value={answer} onChange={handleChange} />
		
		  <Button onClick={() => getData(false)}>
				  Next question
		  </Button>
		  <Button onClick={bookmarkQuestion}>
		  		Bookmark Question
		  </Button>
		  <Button onClick={() => validate_answer(db_answer)}>
				  Check Correctness
		  </Button>
		  <Button onClick={done}>
		  		Done
		  </Button>
      </header>
    </div>
  );
}

export default App;
