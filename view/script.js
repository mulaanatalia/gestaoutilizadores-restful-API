const url = "http://localhost:8080/task/user/1"; //a url tratá todas tasks de um user - user 1

function hideLoader() { //apaga o loading quando a API iniciar
  document.getElementById("loading").style.display = "none";
}

function show(tasks) { //Preenche a tabela, na funçao nao é preciso indicar o tipo do parametro - cria a tabela no momento da execução
  let tab = `<thead>
            <th scope="col">#</th> 
            <th scope="col">Description</th>
            <th scope="col">Username</th>
            <th scope="col">User Id</th>
        </thead>`;

  for (let task of tasks) {
    tab += `
            <tr>
                <td scope="row">${task.id}</td>
                <td>${task.description}</td>
                <td>${task.user.username}</td>
                <td>${task.user.id}</td>
            </tr>
        `;
  }

  document.getElementById("tasks").innerHTML = tab;//para substituir o que está na pag. html na parte de table
}

async function getAPI(url) { //para aceder a API - É async pois vai carregar depois que a página carregar
  const response = await fetch(url, { method: "GET" });//recebe o método do fetch

  var data = await response.json(); //busca os dados recebidos no response
  console.log(data);
  if (response) hideLoader();
  show(data);
}

getAPI(url);//pega a url que criamos no inicio do arquivo