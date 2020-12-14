function createList(){
    let create = prompt("Type in new name")
        let data =  {
            "name": create
        }
        fetch("http://localhost:9092/tasklist/create",{
            method: 'post',
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            },
            body:JSON.stringify(data)
        })
    refresh(1000)
}