//const url= "http://localhost:8080/ name of local host
// what is my url for my server??

document.getElementById("loginbtn").addEventListener("click", loginFunc);

async function loginFunc() {
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {
        username : usern, 
        password : userp
    }

    let resp = await fetch(url+"login", {
    method: 'POST',
    body: JSON.stringify(user),
    credentials: "include"
})

    if(resp.status===200){
        console.log(resp)
        document.getElementById("login-row").innerText = "You have logged in.";
        let button =  document.createElement("button");
        button.className = "btn btn-success";
        button.id = "findAllBtn";
        button.innerText = "Find All Reimbursments";
        button.onclick = findAllFucn();
        document.getElementById("table-row").appendChild(button);
    } else {
        document.getElementById("login-row").innerText = "Login failed.";
    }

}

async function findAllFucn() {

    let resp = await fetch(url+"reimbursement", {
        credentials: "include"
    });

    if(resp ===200) {
        let data = await resp.json();
        console.log(data);
    }
}