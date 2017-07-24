checkUserStatus();

function selectHelper() {
    console.log("helper");
    sendPost("Desk", "type=enter queue&queueName=helpers", handleResponse);
}

function selectClient() {
    console.log("client");
    sendPost("Desk", "type=enter queue&queueName=users", handleResponse);
}

function handleResponse(res) {
    console.log(res);
    res = JSON.parse(res);
    if (res.status === "waiting") document.location = "waiting_room.html";
    if (res.status === "chatting") document.location = "room.html";
}