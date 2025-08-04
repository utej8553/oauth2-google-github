fetch("/user")
    .then(res => {
        if (res.ok) return res.json();
        throw new Error("Not logged in");
    })
    .then(user => {
        document.getElementById("login-section").style.display = "none";
        document.getElementById("user-section").style.display = "block";
        document.getElementById("user-name").textContent = user.name || user.login;
        document.getElementById("user-email").textContent = user.email || "";
        document.getElementById("user-pic").src = user.picture || user.avatar_url || "";
    })
    .catch(() => {
        document.getElementById("login-section").style.display = "block";
        document.getElementById("user-section").style.display = "none";
    });
