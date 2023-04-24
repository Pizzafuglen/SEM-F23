function loadCurrentState() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("charger").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "http://localhost:8001/MoveToStorageOperation", true);
    xhttp.send();
}