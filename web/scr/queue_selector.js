function selectHelper() {
    console.log("helper");
    sendPost("Desk", "type=enter queue&queueName=helpers", handleResponse);
}

function selectClient() {
    console.log("client");
    sendPost("Desk", "type=enter queue&queueName=users", handleResponse);
}

function handleResponse(res) {
    res = JSON.parse(res);
    console.log(res);
    if (res.status === "waiting") {
        document.location = "waiting_room.html";
    }
    document.location = "waiting_room.html";
}