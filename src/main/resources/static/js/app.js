var shareApp
class ReminderApp {
    constructor(sf) {
        this.sf = sf
        sf.userRegisterBtn.onclick = this.login
        sf.userLoginBtn.onclick = this.login
    }

    login(e) {
        e.preventDefault()
        let xhr = new XMLHttpRequest()
        let data = { nickname: '안녕', password: '세상' }

        xhr.addEventListener('load', function (e) {
            console.log(this, e)
            if (this.status != 200)
                this.responseText = 'Ajax Failed'
        })
        xhr.open('POST', '/user/login', true)
        xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8')
        xhr.send(JSON.stringify(data))
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