
 //get all reimbursements button
 let button =  document.createElement("button");
 button.className = "btn btn-success";
 button.id = "findAllBtn";
 button.innerText = "All Reimbursements";
 button.onclick = findAllFunc;
 document.getElementById("table-row").appendChild(button);
 //find by status button
 let button2 = document.createElement('button');
 button2.className = "btn btn-success";
 button2.id = "filterByStatusBtn";
 button2.innerText = "Find Reimbursements By Status";
 button2.onclick = filterFunc;
 document.getElementById("table-row").appendChild(button2);
 //update status button
 let button4 = document.createElement('button');
 button4.className = "btn btn-success";
 button4.id = "filterByStatusBtn";
 button4.innerText = "Update Reimbursement Status";
 button4.onclick = updateFunc;
 document.getElementById("table-row").appendChild(button4);
 //logout button
 let button3 =  document.createElement("button");
 button3.className = "btn btn-success";
 button3.id = "logoutBtn";
 button3.innerText = "Logout";
 button3.onclick = logout;
 document.getElementById("table-row").appendChild(button3);

 async function findAllFunc() {

    document.getElementById("reimbbody").innerText ="";

    let resp = await fetch(url+"reimbursement", {
        credentials: "include",
    });

    if(resp.status === 200) {
        let data = await resp.json();
        console.log(data);
        for (let reimbursement of data) {
            console.log(reimbursement);
           let row = document.createElement("tr");
           let cell = document.createElement("td");
           cell.innerHTML = reimbursement.reimbID;
           row.appendChild(cell);
           let cell2 = document.createElement("td");
           cell2.innerHTML = "$" + reimbursement.reimbAmount;
           row.appendChild(cell2);
          let cell3 = document.createElement("td");
           cell3.innerHTML = reimbursement.reimbType;
           row.appendChild(cell3);
           let cell4 = document.createElement("td");
           cell4.innerHTML = reimbursement.reimbStatus;
           row.appendChild(cell4);
           let cell9 = document.createElement("td");
           cell9.innerHTML = reimbursement.reimbDescription;
           row.appendChild(cell9);
           let cell5 = document.createElement("td");
           cell5.innerHTML = reimbursement.reimbAuthor.username;
           row.appendChild(cell5);
           if (reimbursement.reimbResolver != null) {
                let cell6 = document.createElement("td");
                cell6.innerHTML = reimbursement.reimbResolver.username;
                row.appendChild(cell6);
           } else {
                let cell6 = document.createElement("td");
                row.appendChild(cell6);
           }
           let cell7 = document.createElement("td");
           var d = new Date(reimbursement.timeSubmitted);
           cell7.innerHTML = d.toLocaleDateString() +"  " +d.toLocaleTimeString();
           row.appendChild(cell7);
           if( reimbursement.timeResolved != null) {
                 let cell8 = document.createElement("td");
                 var d2 = new Date(reimbursement.timeResolved);
                 cell8.innerHTML = d2.toLocaleDateString() +"  " +d2.toLocaleTimeString();
                 row.appendChild(cell8);
           }else {
                let cell8 = document.createElement("td");
                row.appendChild(cell8);
               
           } 

           document.getElementById("reimbbody").appendChild(row);
        }
    }
}

async function filterFunc() {
    let reimbStatus1 = document.getElementById("filterStatus").value;
    let status = {
        filterStatus : reimbStatus1
    }
    document.getElementById("reimbbody").innerText ="";

    let resp = await fetch(url+"filter/" + reimbStatus1, {
        credentials: "include",
    });

    if(resp.status === 200) {
        let data = await resp.json();
        console.log(data);
        for (let reimbursement of data) {
            console.log(reimbursement);
           let row = document.createElement("tr");
           let cell = document.createElement("td");
           cell.innerHTML = reimbursement.reimbID;
           row.appendChild(cell);
           let cell2 = document.createElement("td");
           cell2.innerHTML = "$" + reimbursement.reimbAmount;
           row.appendChild(cell2);
          let cell3 = document.createElement("td");
           cell3.innerHTML = reimbursement.reimbType;
           row.appendChild(cell3);
           let cell4 = document.createElement("td");
           cell4.innerHTML = reimbursement.reimbStatus;
           row.appendChild(cell4);
           let cell9 = document.createElement("td");
           cell9.innerHTML = reimbursement.reimbDescription;
           row.appendChild(cell9);
           let cell5 = document.createElement("td");
           cell5.innerHTML = reimbursement.reimbAuthor.username;
           row.appendChild(cell5);
           if (reimbursement.reimbResolver != null) {
                let cell6 = document.createElement("td");
                cell6.innerHTML = reimbursement.reimbResolver.username;
                row.appendChild(cell6);
           } else {
                let cell6 = document.createElement("td");
                row.appendChild(cell6);
           }
           let cell7 = document.createElement("td");
           var d = new Date(reimbursement.timeSubmitted);
           cell7.innerHTML = d.toLocaleDateString() +"  " +d.toLocaleTimeString();
           row.appendChild(cell7);
           if( reimbursement.timeResolved != null) {
                 let cell8 = document.createElement("td");
                 var d2 = new Date(reimbursement.timeResolved);
                 cell8.innerHTML = d2.toLocaleDateString() +"  " +d2.toLocaleTimeString();
                 row.appendChild(cell8);
           }else {
                let cell8 = document.createElement("td");
                row.appendChild(cell8);
               
           } 

           document.getElementById("reimbbody").appendChild(row);
        }
    }
}

async function updateFunc() {
    let reimbStatus2 = document.getElementById("updateStatus").value;
    let reimbStatus3 = document.getElementById("updateStatus1").value;
    let reimbStatus4 = document.getElementById("resolverUserName").value;
    let status = {
        reimbID: reimbStatus3,
        resolverUserName: reimbStatus4,
        reimbStatus : reimbStatus2
    }
    document.getElementById("reimbbody").innerText ="";

    let resp = await fetch(url+"updateStatus", {
        method: 'POST',
        body: JSON.stringify(status),
        credentials: "include",
    });
    if (resp.status === 201) {
        console.log(resp);
       // location.reload();
        document.getElementById("reimbbody").innerText = "";
        findAllFunc();
    }
}
async function logout() {

    let resp = await fetch(url + "logout", {
        credentials: 'include',
    });
    if (resp.status === 200) {
        console.log("logout");
        window.location.href = "index.html";
        document.getElementById("login-row").innerText = "You been logged out.";
    }
}