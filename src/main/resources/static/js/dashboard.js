// Check authentication on page load
document.addEventListener("DOMContentLoaded", () => {

    const token = localStorage.getItem("token");

    if (!token) {
        window.location.href = "/pages/login.html";
        return;
    }

    loadUserProfile();
});


// Load user data from localStorage
function loadUserProfile() {

    const email = localStorage.getItem("email");
    const phone = localStorage.getItem("phone");
    const role = localStorage.getItem("role");
    const id = localStorage.getItem("id");

    document.getElementById("email").textContent = email || "N/A";
    document.getElementById("phone").textContent = phone || "N/A";
}


// Logout function
document.getElementById("logoutBtn").addEventListener("click", logout);

function logout(){

    localStorage.removeItem("token");
    localStorage.removeItem("email");
    localStorage.removeItem("phone");
    localStorage.removeItem("role");
    localStorage.removeItem("id");

    window.location.href = "login.html";
}