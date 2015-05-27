var formList = document.querySelectorAll('.answerWrite input[type=submit]');
var delbuttons = document.querySelectorAll('.del');
var questionId = document.querySelector('#questionId');

for ( var j=0 ; j < formList.length ; j++) {
	formList[j].addEventListener('click', writeAnswers, false);
}

for(var k=0; k <delbuttons.length ; k++) {
	delbuttons[k].addEventListener('click',deleteAnswers, false);
}


function writeAnswers(e) {
	 e.preventDefault();
	 
	 var answerForm = e.currentTarget.form;
	 var url = "/api/addanswer.next";
	 var params = "questionId=" + answerForm[0].value + "&writer=" + answerForm[1].value + "&contents=" + answerForm[2].value;

	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
			 location.reload(true);
		 }
	 }
	 
	 request.send(params);
}
function deleteAnswers(e) {
	 e.preventDefault();
	 var answerId = e.currentTarget.getAttribute('data-answerId');
	 var qId = questionId.value; 
	 
	 
	 var url = "/api/deleteAnswer.next";
	 var params = "questionId=" + qId + "&answerId=" + answerId;

	 console.log(params);
	 
	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
			 location.reload(true);
		 }
	 }
	 
	 request.send(params);
}
