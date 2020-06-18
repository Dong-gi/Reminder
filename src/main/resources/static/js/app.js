var shareApp
class ReminderApp {
    constructor(sf) {
        this.sf = sf
        sf.userRegisterBtn.onclick = this.register
        sf.userLoginBtn.onclick = this.login
        this.loadToken()
    }

    loadToken() {

    }

    saveToken() {

    }

    clearToken() {

    }

    register(e) {
        e.preventDefault()
        let data = SF.formToObject(shareApp.sf.login.$)
        if (!ReminderApp.checkLoginForm(data))
            return
        ReminderApp.apiRequest('POST', '/user/register', data, (result) => {
            console.log(result)
        })
    }

    login(e) {
        e.preventDefault()
        let data = SF.formToObject(shareApp.sf.login.$)
        if (!ReminderApp.checkLoginForm(data))
            return
        ReminderApp.apiRequest('POST', '/user/login', data, (result) => {
            console.log(result)
        })
    }

    static apiRequest(method, api, data, callback) {
        let xhr = new XMLHttpRequest()
        xhr.addEventListener('load', ((callback) => function (e) {
            console.log(this, e)
            if (this.status != 200) {
                ReminderApp.showSnackbar('연결 실패... 잠시 후 다시 시도하세요')
                callback(null)
            } else
                callback(this.responseText)
        })(callback))
        xhr.open(method, api, true)
        xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8')
        xhr.send(JSON.stringify(data))
    }

    static checkLoginForm(data) {
        if (!data.nickname) {
            ReminderApp.showSnackbar('닉네임을 입력하세요')
            ReminderApp.highlight(shareApp.sf['login-nickname'])
            return false
        }
        if (!data.password) {
            ReminderApp.showSnackbar('비밀번호를 입력하세요')
            ReminderApp.highlight(shareApp.sf['login-password'])
            return false
        }
        return true
    }

    static showSnackbar(text, parent, timeout) {
        let hiddenElement = `<div id="snackbar" class="show">${text}</div>`.asSF().$;
        (parent || document.body).append(hiddenElement)
        setTimeout(function () {
            hiddenElement.remove()
        }, timeout || 1000)
    }
    static highlight(target) {
        target = target.$ || target
        target.style.animation = ''
        setTimeout(((target) => function () {
            target.style.animation = 'highlight 2s 1'
        })(target), 139)
    }
}

window.addEventListener('load', () => {
    const app = new ReminderApp(SF.build())
    Object.freeze(app)
    shareApp = app
})

function closeSidebar() {
    shareApp.sf.nav.$.classList.add('w3-hide')
}
function openSidebar() {
    shareApp.sf.nav.$.classList.remove('w3-hide')
}
function toggleSidebar() {
    if (shareApp.sf.nav.$.classList.contains('w3-hide'))
        openSidebar()
    else
        closeSidebar()
}