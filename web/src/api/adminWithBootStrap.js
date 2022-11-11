var addGuestButton = document.getElementById("addGuestButton");

addGuestButton.addEventListener("click", addGuest)

function addGuest() {
    let firstNameValue = document.getElementById("firstName");
    let lastNameValue = document.getElementById("lastName");

    const params = {
        param1: firstNameValue,
        param2: lastNameValue;
    };

    const options = {
        method: 'POST',
        body: JSON.stringify( params )
    };

    fetch( 'placeholder', options)
        .then( response => response.json())
        .then( response => alert("Guest added!"))
}