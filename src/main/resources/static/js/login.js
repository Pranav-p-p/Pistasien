console.log("login.js loaded");
const form = document.getElementById("loginForm");

form.addEventListener("submit", function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch("http://localhost:8080/auth/login" ,{
        method : "POST",

        headers : {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(response => response.json())
    .then(data => {
        console.log("Server response:", data);

        localStorage.setItem("token", data.loginToken);

        window.location.href = "dashboard.html";
    })
    .catch(error => console.error("Error:", error));
});