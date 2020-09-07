
 //get all reimbursements button
 let button =  document.createElement("button");
 button.className = "btn btn-success";
 button.id = "findAllBtn";
 button.innerText = "Find All Reimbursements";
 button.onclick = findAllFunc;
 document.getElementById("table-row").appendChild(button);
 //add a reimbursement button
 let button2 = document.createElement('button');
 button2.className = "btn btn-success";
 button2.id = "addReimbBtn";
 button2.innerText = "Add a Reimbursement";
 button2.onclick = AddFunc;
 document.getElementById("formbtn").appendChild(button2);
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
                 cell8.innerHTML = reimbursement.timeResolved;
                 row.appendChild(cell8);
           }else {
                let cell8 = document.createElement("td");
                row.appendChild(cell8);
               
           } 

           document.getElementById("reimbbody").appendChild(row);
        }
    }
}

async function AddFunc() {
    let reimbAmount1 = document.getElementById("reimbAmount").value;
    let reimbType1 = document.getElementById("reimbType").value;
    let reimbDescription1 = document.getElementById("reimbDescription").value;
    let authorUserName1 = document.getElementById("authorUserName").value;
    
    let reimbursement = {
        reimbAmount : reimbAmount1,
        reimbType : reimbType1,
        reimbDescription : reimbDescription1,
        authorUserName : authorUserName1
    }

    let resp = await fetch(url+"reimbursement", {
        method: 'POST',
        body: JSON.stringify(reimbursement),
        credentials: "include"
    })

    if(resp.status === 200){
        findAllFunc()
        document.getElementById("login-row").innerText = "Reimbursement was created.";
    } else {
        document.getElementById("login-row").innerText = "Reimbursement could not be created.";
    }


}