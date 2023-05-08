// Function to redirect to another URL
function redirectTo(url){
    window.location.href = url;
}

// js function for the home page
// add DOMContentLoaded event listener to execute the code
document.addEventListener('DOMContentLoaded',function(){
    // get the button element
var redirectButton = document.getElementById("redirectToHome");

//add a click event listener to the button
redirectButton.addEventListener('click', function(){
//send the url and redirect
var urlToSend = "http://127.0.0.1:5500/FrontEnd/recipe.html"
redirectTo(urlToSend);
});
})

// js function for the log page
// add DOMContentLoaded event listener to execute the code
document.addEventListener('DOMContentLoaded',function(){
    // get the button element
var redirectButton = document.getElementById("redirectToLog");

//add a click event listener to the button
redirectButton.addEventListener('click', function(){
//send the url and redirect
var urlToSend = "http://127.0.0.1:5500/FrontEnd/log.html"
redirectTo(urlToSend);
});
})



