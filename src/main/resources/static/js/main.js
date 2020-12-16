function refresh( time ) {
    setTimeout("location.reload();", time);
}

fetch('http://localhost:9092/tasklist/read')
    .then(
        function(response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
            }
            response.json().then(function (data){
                for(const i of data) {

                    const div = document.createElement('div')
                    const h1 = document.createElement('h1')

                    const taskcreate = document.createElement('a')
                    const taskcreatei = document.createElement('i')
                    taskcreatei.className = "fas fa-plus-square"
                    taskcreate.onclick = function(){let create = prompt("Type in new name")
                        let data =  {
                            "name": create,
                            "tasks":{
                                "id": i.id
                            }
                        }
                        fetch("http://localhost:9092/task/create",{
                            method: 'post',
                            headers: {
                                "Content-type": "application/json; charset=UTF-8"
                            },
                            body:JSON.stringify(data)
                        })
                        refresh(1000)
                    }
                    taskcreate.appendChild(taskcreatei)

                    const taskedit = document.createElement('a')
                    const taskediti = document.createElement('i')
                    taskediti.className = "fas fa-edit"
                    taskedit.onclick = function(){let update = prompt("Type in new name")
                        let data =  {
                            "name": update
                        }
                        fetch("http://localhost:9092/tasklist/update/"+i.id,{
                            method: 'put',
                            headers: {
                                "Content-type": "application/json; charset=UTF-8"
                            },
                            body:JSON.stringify(data)
                        })
                        refresh(1000)
                    }
                    taskedit.appendChild(taskediti)

                    const taskdel = document.createElement('a')
                    const taskdeli = document.createElement('i')
                    taskdeli.className = "fas fa-trash-alt"
                    taskdel.onclick = function(){fetch("http://localhost:9092/tasklist/delete/"+i.id,{method: 'delete'})
                        refresh(1000)}
                    taskdel.appendChild(taskdeli)

                    h1.innerText = i.name
                    h1.appendChild(taskcreate)
                    h1.appendChild(taskedit)
                    h1.appendChild(taskdel)

                    const ul = document.createElement('ul')
                    for (const j of i.tasks){
                        const li = document.createElement('li')

                        const edit = document.createElement('a')
                        const editi = document.createElement('i')
                        editi.className = "fas fa-edit"
                        edit.onclick = function(){let update = prompt("Type in new name")
                            let data =  {
                                            "name": update
                                        }
                            fetch("http://localhost:9092/task/update/"+j.id,{
                                method: 'put',
                                headers: {
                                    "Content-type": "application/json; charset=UTF-8"
                                },
                                body:JSON.stringify(data)
                            })
                            refresh(1000)
                            }
                        edit.appendChild(editi)

                        const del = document.createElement('a')
                        const deli = document.createElement('i')
                        deli.className = "fas fa-trash-alt"
                        del.onclick = function(){fetch("http://localhost:9092/task/delete/"+j.id,{method: 'delete'})
                            refresh(1000)}
                        del.appendChild(deli)

                        li.innerText = j.name
                        li.appendChild(edit)
                        li.appendChild(del)
                        ul.appendChild(li)
                    }

                    div.appendChild(h1)
                    div.appendChild(ul)
                    document.body.appendChild(div)
                }
            })
        }
    )
    .catch(function(err) {
        console.log('Fetch Error :-S', err)
    })