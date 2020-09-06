const url= "http://localhost:8080/project0/";


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
        for (let reimbursement of data) {
            console.log(reimbursement);
           let row = document.createElement("tr");
           let cell = document.createElement("td");
           cell.innerHTML = reimbursement.reimbID;
           row.appendChild(cell);
           let cell2 = document.createElement("td");
           cell2.innerHTML = reimbursement.reimbAmount;
           row.appendChild(cell2);
           let cell3 = document.createElement("td");
           cell3.innerHTML = reimbursement.reimbType;
           row.appendChild(cell3);
           let cell4 = document.createElement("td");
           cell4.innerHTML = reimbursement.reimbStatus;
           row.appendChild(cell4);
           let cell5 = document.createElement("td");
           cell5.innerHTML = reimbursement.reimbAuthor;
           row.appendChild(cell5);
           let cell6 = document.createElement("td");
           cell6.innerHTML = reimbursement.reimbResolver;
           row.appendChild(cell6);
           let cell7 = document.createElement("td");
           cell7.innerHTML = reimbursement.timeSubmitted;
           row.appendChild(cell7);
           let cell8 = document.createElement("td");
           cell8.innerHTML = reimbursement.reimbResolved;
           row.appendChild(cell8);

           document.getElementById("reimbbody").appendChild(row);
        }
    }
}