function initiateProd() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText)
            document.getElementById("prodOutput").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "http://localhost:7999/initProdDrone", true);
    xhttp.send();
}
