function sendPost(url, params, callback) {
    http = new XMLHttpRequest();
    http.open('POST', url);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.onreadystatechange = function() {
        if(http.readyState === XMLHttpRequest.DONE && http.status === 200) {
            callback(http.responseText);
        } else if (this.status === 403) {
            window.location.href = 'login.html';
        }
    };
    http.send(params);
}

function getCookies () {
    let arr = document.cookie.split('; ');
    let cookies = {};
    arr.forEach(function (c) {
        let cs = c.split('=');
        cookies[cs[0]] = cs[1];
    });
    return cookies;
}

function scrollToBootom() {
    window.scrollTo(0,document.body.scrollHeight);
}

function logout() {
    document.cookie = "token=expired";
    document.location = "login.html";
}