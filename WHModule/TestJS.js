function InsertPost() {
    fetch('https://localhost:8001/1/InsertItemInventory', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(19)
    })
        .then(response => response.json())
        .then(response => console.log(JSON.stringify(response)))
}

document.getElementById("test").addEventListener("click", InsertPost, false)