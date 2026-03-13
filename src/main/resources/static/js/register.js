const button = document.getElementById("actionBtn");

let step = 1;

button.addEventListener("click", function () {

    const phone = document.getElementById("phone").value;

    if (step === 1) {

        fetch("http://localhost:8080/auth/sentOtp", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                phone: phone
            })
        })
        .then(async response => {

            const data = await response.json();

                if (!response.ok) {
                    throw new Error(data.error);
                }

                return data;

        })
        .then(data => {

            document.getElementById("message").innerText = "";

            document.getElementById("otpSection").style.display = "block";

            document.getElementById("phone").readOnly = true;

            button.innerText = "Verify OTP";

            step = 2;

        })
        .catch(error => {

            document.getElementById("message").innerText =
                error.message;

        });

    } else if (step === 2) {

        const otp = document.getElementById("otp").value;

        fetch("http://localhost:8080/auth/verifyOtp", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                phone: phone,
                otp: otp
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

            document.getElementById("message").innerText = "";

            document.getElementById("phone").readOnly = true;

            document.getElementById("otpSection").style.display = "none";

            document.getElementById("userDetails").style.display = "block";

            button.innerText = "Register";

            step = 3;

        })
           .catch(error => {
           document.getElementById("message").innerText =
                           error.message;
       });

    } else if (step === 3) {

        const email = document.getElementById("email").value;
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const adsConsent = document.getElementById("adsConsent").checked;

        fetch("http://localhost:8080/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                phone: phone,
                userEmail: email,
                userName: username,
                password: password,
                option: adsConsent
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

            document.getElementById("message").innerText =
                "Registration successful";

                window.location.href = "login.html";

        })
        .catch(error => {
            document.getElementById("message").innerText =
                                       error.message;
        });
    }
});