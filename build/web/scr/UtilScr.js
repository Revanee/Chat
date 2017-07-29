function sendPost(url, params, callback) {
    http = new XMLHttpRequest();
    http.open('POST', url);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 200) {
            if (callback)
                callback(http.responseText);
        } else if (this.status === 403) {
            window.location.href = 'login.html';
        }
    };
    http.send(params);
}

function getCookies() {
    let arr = document.cookie.split('; ');
    let cookies = {};
    arr.forEach(function (c) {
        let cs = c.split('=');
        cookies[cs[0]] = cs[1];
    });
    return cookies;
}

function scrollToBootom() {
    window.scrollTo(0, document.body.scrollHeight);
}

function logout() {
    sendPost("Logout");
    document.location.href = "Login";
}

function checkUserStatus() {
    sendPost("Desk", "type=check status", function (res){
        res = JSON.parse(res);
        if (res.status === "waiting") ensureLocation("waiting_room.html");
        if (res.status === "chatting") ensureLocation("room.html");
        if (res.status === "idle") ensureLocation("queue_selector.html");
    });
}

function getLocation() {
    return window.location.href.substr(window.location.href.lastIndexOf('/'));
}

function ensureLocation(loc) {
    if (getLocation() !== "/" + loc) window.location.href = loc;
}
