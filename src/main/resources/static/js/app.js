function login() {
    let xhr = new XMLHttpRequest();
    let data = { nickname: '안녕', password: '세상' };

    xhr.addEventListener('load', function (e) {
        console.log(this, e);
        if (this.status != 200)
            this.responseText = 'Ajax Failed';
    });
    xhr.open('POST', '/user/login', true);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8')
    xhr.send(JSON.stringify(data));
}