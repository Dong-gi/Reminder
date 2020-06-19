var shareApp
window.addEventListener('load', () => {
    const app = new ReminderApp(SF.build())
    Object.freeze(app)
    shareApp = app
})
const CONSTS = {
    KEY_USER_ID: 'userId',
    KEY_ALWAYS_LOGIN: 'alwaysLogin',
    KEY_NICKNAME: 'nickname',
    KEY_PASSWORD: 'password',
    KEY_REQUEST_TOKEN: 'requestToken',
    KEY_RESULT_CODE: 'apiResultCode',

    API_USER_REGISTER: '/user/register',
    API_USER_LOGIN: '/user/login',

    MSG_AJAX_FAIL: '연결 실패... 잠시 후 다시 시도하세요',
    MSG_NO_NICKNAME: '닉네임을 입력하세요',
    MSG_NICKNAME_EXISTS: '이미 등록된 닉네임입니다',
    MSG_NO_PASSWORD: '비밀번호를 입력하세요',
    MSG_TOKEN_EXPIRED: '정확한 정보로 재로그인이 필요합니다',
    MSG_LOGIN_SUCCESS: '로그인 성공!',
}
const UI_FUNCS = {
    HIDE: (node) => (node.$ || node).classList.add('w3-hide'),
    SHOW: (node) => (node.$ || node).classList.remove('w3-hide'),
    SHOW_SNACKBAR: (text, parent, timeout) => {
        let hiddenElement = `<div id="snackbar" class="show">${text}</div>`.asSF().$;
        (parent || document.body).append(hiddenElement)
        setTimeout(function () {
            hiddenElement.remove()
        }, timeout || 1000)
    },
    HIGHLIGHT: (target) => {
        target = target.$ || target
        target.style.animation = ''
        setTimeout(((target) => function () {
            target.style.animation = 'highlight 2s 1'
        })(target), 139)
    },
    CLOSE_SIDEBAR: (e) => {
        e.preventDefault()
        UI_FUNCS.HIDE(shareApp.sf.nav)
    },
    TOGGLE_SIDEBAR: (e) => {
        e.preventDefault()
        if (shareApp.sf.nav.classList.contains('w3-hide'))
            UI_FUNCS.SHOW(shareApp.sf.nav)
        else
            UI_FUNCS.HIDE(shareApp.sf.nav)
    }
}
const API_FUNCS = {
    REQUEST: (method, api, data, callback) => {
        let xhr = new XMLHttpRequest()
        xhr.addEventListener('load', ((callback) => function (e) {
            console.log(e)
            if (this.status != 200) {
                ReminderApp.showSnackbar(CONSTS.MSG_AJAX_FAIL)
                callback({})
            } else {
                let result = JSON.parse(this.responseText)
                switch (result[CONSTS.KEY_RESULT_CODE]) {
                    case 400: case 1003:
                        UI_FUNCS.SHOW_SNACKBAR(CONSTS.MSG_TOKEN_EXPIRED)
                        break
                    default:
                        callback(result)
                }
            }
        })(callback))
        xhr.open(method, api, true)
        xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8')
        xhr.send(JSON.stringify(data))
    },
    CALLBACK_LOGIN: (result) => {
        console.log(result)
        switch (result[CONSTS.KEY_RESULT_CODE]) {
            case 200:
                UI_FUNCS.SHOW_SNACKBAR(CONSTS.MSG_LOGIN_SUCCESS)
                UI_FUNCS.HIDE(shareApp.sf.login)
                UI_FUNCS.SHOW(shareApp.sf.reminder)
                if (VALIDATES.STORAGE_TRUE(CONSTS.KEY_ALWAYS_LOGIN))
                    localStorage.setItem(CONSTS.KEY_USER_ID, result[CONSTS.KEY_USER_ID])
                localStorage.setItem(CONSTS.KEY_REQUEST_TOKEN, result[CONSTS.KEY_REQUEST_TOKEN])
                break
            case 1004:
                UI_FUNCS.HIGHLIGHT(shareApp.sf['login-nickname'])
                UI_FUNCS.SHOW_SNACKBAR(CONSTS.MSG_NICKNAME_EXISTS)
                break
            default:
                UI_FUNCS.SHOW_SNACKBAR(CONSTS.MSG_TOKEN_EXPIRED)
                break
        }
    }
}
const VALIDATES = {
    STORAGE_TRUE: (key) => localStorage.getItem(key) && 'true'.search(localStorage.getItem(key)) >= 0,
    LOGIN_FORM: (data) => {
        if (!data[CONSTS.KEY_NICKNAME]) {
            UI_FUNCS.SHOW_SNACKBAR(CONSTS.MSG_NO_NICKNAME)
            UI_FUNCS.HIGHLIGHT(shareApp.sf['login-nickname'])
            return false
        }
        if (!data[CONSTS.KEY_PASSWORD]) {
            UI_FUNCS.SHOW_SNACKBAR(CONSTS.MSG_NO_PASSWORD)
            UI_FUNCS.HIGHLIGHT(shareApp.sf['login-password'])
            return false
        }
        return true
    }
}
class ReminderApp {
    constructor(sf) {
        this.sf = sf
        this.restoreSession()
        sf.closeSidebarBtn.onclick = UI_FUNCS.CLOSE_SIDEBAR
        sf.toggleSidebarBtn.onclick = UI_FUNCS.TOGGLE_SIDEBAR
        sf.userRegisterBtn.onclick = this.register
        sf.userLoginBtn.onclick = this.login
    }

    restoreSession() {
        if (VALIDATES.STORAGE_TRUE(CONSTS.KEY_ALWAYS_LOGIN)) {
            let data = {}
            data[CONSTS.KEY_USER_ID] = localStorage.getItem(CONSTS.KEY_USER_ID)
            data[CONSTS.KEY_REQUEST_TOKEN] = localStorage.getItem(CONSTS.KEY_REQUEST_TOKEN)
            data[CONSTS.KEY_ALWAYS_LOGIN] = localStorage.getItem(CONSTS.KEY_ALWAYS_LOGIN)
            API_FUNCS.REQUEST('POST', CONSTS.API_USER_LOGIN, data, API_FUNCS.CALLBACK_LOGIN)
        }
    }

    register(e) {
        e.preventDefault()
        let data = SF.formToObject(shareApp.sf.login.$)
        if (!VALIDATES.LOGIN_FORM(data))
            return
        localStorage.setItem(CONSTS.KEY_ALWAYS_LOGIN, data[CONSTS.KEY_ALWAYS_LOGIN])
        API_FUNCS.REQUEST('POST', CONSTS.API_USER_REGISTER, data, API_FUNCS.CALLBACK_LOGIN)
    }

    login(e) {
        e.preventDefault()
        let data = SF.formToObject(shareApp.sf.login.$)
        if (!VALIDATES.LOGIN_FORM(data))
            return
        localStorage.setItem(CONSTS.KEY_ALWAYS_LOGIN, data[CONSTS.KEY_ALWAYS_LOGIN])
        API_FUNCS.REQUEST('POST', CONSTS.API_USER_LOGIN, data, API_FUNCS.CALLBACK_LOGIN)
    }
}
Object.freeze(ReminderApp)