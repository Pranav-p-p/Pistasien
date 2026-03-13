console.log("login.js loaded");
const form = document.getElementById("loginForm");

form.addEventListener("submit", function(event) {
    event.preventDefault();

    const input = document.getElementById("input").value;
    const password = document.getElementById("password").value;

    fetch("http://localhost:8080/auth/login" ,{
        method : "POST",

        headers : {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            input: input,
            password: password
        })
    })
    .then(async response => {

                        const data = await response.json();

                        if(!response.ok){
                            throw new Error(data.error);

                       }
                       return data;

                    })
    .then(data => {
        console.log("Server response:", data);

        localStorage.setItem("token", data.loginToken);
        localStorage.setItem("id", data.user.loginResponseId);
        localStorage.setItem("phone", data.user.loginResponsePhone);
        localStorage.setItem("email", data.user.loginResponseEmail);
        localStorage.setItem("role", data.user.loginResponseRole);

        window.location.href = "dashboard.html";
    })
    .catch(error => {
                document.getElementById("message").innerText =
                                           error.message;
            });
});