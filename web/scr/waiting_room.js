startPollingStatus();

function startPollingStatus() {
    setTimeout( function () {
        sendPost("Desk", "type=check status", function (res) {
            handleResponse(res);
        });
        startPollingStatus();
    }, 1000);
}

function handleResponse(res) {
    res = JSON.parse(res);
    if (res === null) document.location = "login.html";
    if (res.status === "chatting") document.location = "room.html";
    console.log(res);
    document.getElementById("status").innerHTML = res.status;
    document.getElementById("queue").innerHTML = res.queue;
}