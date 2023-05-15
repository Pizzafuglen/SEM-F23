function loadCurrentOperation() {
    console.log("hi");
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

    };
    xhttp.open("GET", "http://localhost:8001/" + document.getElementById("operation").value, true); // post istedet for get
    xhttp.send();
}

let loadCurrentState= () =>{
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

    };
    xhttp.open("GET", "http://localhost:8001/2", true); 
    xhttp.send();

}