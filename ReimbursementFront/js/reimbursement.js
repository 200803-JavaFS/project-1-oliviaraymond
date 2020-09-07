const url= "http://localhost:8080/project0/";


document.getElementById("loginbtn").addEventListener("click", loginFunc);

async function loginFunc() {
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {
        username : usern, 
        password : CryptoJS.MD5(userp).toString().toUpperCase()
    }

    let resp = await fetch(url+"login", {
    method: 'POST',
    body: JSON.stringify(user),
    credentials: "include"
})

    if(resp.status===200){
        console.log(resp)
        document.getElementById("login-row").innerText = "You have logged in.";
        loggedIn();
    } else {
        document.getElementById("login-row").innerText = "Login failed.";
    }
}

//sends user to correct page based on their type
async function loggedIn() {
    let resp = await fetch(url + "loggedIn", {
        method: 'GET',
        credentials: "include"
    });
    console.log(resp.status);
    if (resp.status === 200) {
        let data = await resp.json();
        console.log(data);
        userType = data;

        if (userType === "EMPLOYEE") {
            window.location.href = "employee.html";
        } else if (userType === "FINANCIAL_MANAGER") {
            window.location.href = "manager.html";
        }
    } else {
        document.getElementById("login-row").innerText = "Login failed.";
    }
}