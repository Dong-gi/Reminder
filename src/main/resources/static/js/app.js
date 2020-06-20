var shareApp;
window.addEventListener('load', () => {
    const app = new ReminderApp(SF.build());
    Object.freeze(app);
    shareApp = app;
});
const I18N = {
    KEY_ADD_DATE: 'addDate',
    KEY_ALWAYS_LOGIN: 'alwaysLogin',
    KEY_ATTACH_FILE: 'attachFile',
    KEY_COMPLETE_FLG: 'completeFlg',
    KEY_DEL_FLG: 'delFlg',
    KEY_NICKNAME: 'nickname',
    KEY_PASSWORD: 'password',
    KEY_REMINDER: 'reminder',
    KEY_REMINDER_ID: 'reminderId',
    KEY_REQUEST_TOKEN: 'requestToken',
    KEY_RESULT_CODE: 'apiResultCode',
    KEY_TITLE: 'title',
    KEY_UPD_DATE: 'updDate',
    KEY_USER_ID: 'userId',

    API_REMINDER_LIST: '/reminder/list',
    API_REMINDER_REMOVE: '/reminder/remove',
    API_REMINDER_UPDATE: '/reminder/update',
    API_USER_REGISTER: '/user/register',
    API_USER_LOGIN: '/user/login',

    MSG_AJAX_FAIL: '잠시 후 다시 시도하세요',
    MSG_BAD_REQUEST: '정확히 입력해주세요',
    MSG_DEFAULT_REMINDER_TITLE: () => `알림#${shareApp.reminders.length + 1}`,
    MSG_LOGIN_SUCCESS: '로그인 성공!',
    MSG_LOGOUT: '로그아웃 완료',
    MSG_NICKNAME_EXISTS: '이미 등록된 닉네임입니다',
    MSG_NO_NICKNAME: '닉네임을 입력하세요',
    MSG_NO_PASSWORD: '비밀번호를 입력하세요',
    MSG_NO_TITLE: '일정 이름을 입력하세요',
    MSG_NOT_IMAGE: '이미지 파일을 선택하세요',
    MSG_REMINDER_SAVED: '일정 저장 완료',
    MSG_REMINDER_REMOVED: '일정 삭제 완료',
    MSG_TOKEN_EXPIRED: '재로그인이 필요합니다',
};
const UI_FUNCS = {
    HIDE: (node) => (node.$ || node).classList.add('w3-hide'),
    SHOW: (node) => (node.$ || node).classList.remove('w3-hide'),
    SHOW_SNACKBAR: (text, parent, timeout) => {
        let hiddenElement = `<div id="snackbar" class="show">${text}</div>`.asSF().$;
        (parent || document.body).append(hiddenElement);
        setTimeout(function () {
            hiddenElement.remove();
        }, timeout || 1000);
    },
    HIGHLIGHT: (target) => {
        target = target.$ || target;
        target.style.animation = '';
        setTimeout(((target) => function () {
            target.style.animation = 'highlight 2s 1';
        })(target), 139);
    },
    CLOSE_SIDEBAR: () => UI_FUNCS.HIDE(shareApp.sf.nav),
    TOGGLE_SIDEBAR: () => {
        if (shareApp.sf.nav.classList.contains('w3-hide'))
            UI_FUNCS.SHOW(shareApp.sf.nav);
        else
            UI_FUNCS.HIDE(shareApp.sf.nav);
    }
};
const API_FUNCS = {
    REQUEST: (method, api, data, callback) => {
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('load', ((callback) => function (e) {
            console.log(e);
            if (this.status != 200) {
                UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_AJAX_FAIL);
                callback({});
            } else {
                let result = JSON.parse(this.responseText);
                switch (result[I18N.KEY_RESULT_CODE]) {
                    case 400:
                        UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_BAD_REQUEST);
                        break;
                    case 1003:
                        UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_TOKEN_EXPIRED);
                        break;
                    default:
                        if (result[I18N.KEY_REQUEST_TOKEN])
                            localStorage.setItem(I18N.KEY_REQUEST_TOKEN, result[I18N.KEY_REQUEST_TOKEN]);
                        callback(result);
                }
            }
        })(callback));
        xhr.open(method, api, true);
        xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8');
        xhr.send(data && JSON.stringify(data));
    },
    CALLBACK_LOGIN: (result) => {
        switch (result[I18N.KEY_RESULT_CODE]) {
            case 200:
                UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_LOGIN_SUCCESS);
                UI_FUNCS.HIDE(shareApp.sf.login);
                UI_FUNCS.SHOW(shareApp.sf.reminder);
                if (VALIDATES.STORAGE_TRUE(I18N.KEY_ALWAYS_LOGIN))
                    localStorage.setItem(I18N.KEY_USER_ID, result[I18N.KEY_USER_ID]);
                shareApp.sf['login-nickname'].value = '';
                shareApp.sf['login-password'].value = '';
                API_FUNCS.REQUEST('GET', I18N.API_REMINDER_LIST, null, API_FUNCS.CALLBACK_REMINDER_LIST);
                break;
            case 1004:
                UI_FUNCS.HIGHLIGHT(shareApp.sf['login-nickname']);
                UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_NICKNAME_EXISTS);
                break
            default:
                UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_TOKEN_EXPIRED);
                break
        }
    },
    CALLBACK_REMINDER_LIST: (result) => {
        switch (result[I18N.KEY_RESULT_CODE]) {
            case 200:
                for (let data of result.list) {
                    let item = new ReminderItem(data);
                    shareApp.reminders.push(item);
                    shareApp.sf.reminder.ul.$.append(item.sf.$);
                }
                break;
            default:
                UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_AJAX_FAIL);
                break;
        }
    },
    CALLBACK_REMINDER_REMOVE: ((reminderItem) => function (result) {
        switch (result[I18N.KEY_RESULT_CODE]) {
            case 200:
                UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_REMINDER_REMOVED);
                reminderItem.sf.$.remove();
                reminderItem[I18N.KEY_DEL_FLG] = true;
                break;
            default:
                UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_AJAX_FAIL);
                break;
        }
    }),
    CALLBACK_REMINDER_UPDATE: ((reminderItem) => function (result) {
        switch (result[I18N.KEY_RESULT_CODE]) {
            case 200:
                UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_REMINDER_SAVED);
                reminderItem[I18N.KEY_REMINDER_ID] = result[I18N.KEY_REMINDER_ID];
                break;
            default:
                UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_AJAX_FAIL);
                break;
        }
    }),
};
const VALIDATES = {
    STORAGE_TRUE: (key) => localStorage.getItem(key) && 'true'.search(localStorage.getItem(key)) >= 0,
    LOGIN_FORM: (data) => {
        if (!data[I18N.KEY_NICKNAME]) {
            UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_NO_NICKNAME);
            UI_FUNCS.HIGHLIGHT(shareApp.sf['login-nickname']);
            return false;
        }
        if (!data[I18N.KEY_PASSWORD]) {
            UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_NO_PASSWORD);
            UI_FUNCS.HIGHLIGHT(shareApp.sf['login-password']);
            return false;
        }
        return true;
    }
}
class ReminderApp {
    constructor(sf) {
        this.sf = sf;
        this.reminders = [];
        this[I18N.KEY_REMINDER] = sf.asTemplate(I18N.KEY_REMINDER, document.querySelector('li[template-id=reminder]'));
        sf.closeSidebarBtn.onclick = UI_FUNCS.CLOSE_SIDEBAR;
        sf.toggleSidebarBtn.onclick = UI_FUNCS.TOGGLE_SIDEBAR;
        sf.userRegisterBtn.onclick = this.register;
        sf.userLoginBtn.onclick = this.login;
        sf.logoutBtn.onclick = this.logout;
        sf.newReminderBtn.onclick = this.newReinder;
        this.restoreSession();
    }

    restoreSession() {
        if (VALIDATES.STORAGE_TRUE(I18N.KEY_ALWAYS_LOGIN)) {
            let data = {};
            data[I18N.KEY_USER_ID] = localStorage.getItem(I18N.KEY_USER_ID);
            data[I18N.KEY_REQUEST_TOKEN] = localStorage.getItem(I18N.KEY_REQUEST_TOKEN);
            data[I18N.KEY_ALWAYS_LOGIN] = localStorage.getItem(I18N.KEY_ALWAYS_LOGIN);
            API_FUNCS.REQUEST('POST', I18N.API_USER_LOGIN, data, API_FUNCS.CALLBACK_LOGIN);
        }
    }

    register(e) {
        e.preventDefault();
        let data = SF.formToObject(shareApp.sf.login.$);
        if (!VALIDATES.LOGIN_FORM(data))
            return;
        localStorage.setItem(I18N.KEY_ALWAYS_LOGIN, data[I18N.KEY_ALWAYS_LOGIN]);
        API_FUNCS.REQUEST('POST', I18N.API_USER_REGISTER, data, API_FUNCS.CALLBACK_LOGIN);
    }

    login(e) {
        e.preventDefault();
        let data = SF.formToObject(shareApp.sf.login.$);
        if (!VALIDATES.LOGIN_FORM(data))
            return;
        localStorage.setItem(I18N.KEY_ALWAYS_LOGIN, data[I18N.KEY_ALWAYS_LOGIN]);
        API_FUNCS.REQUEST('POST', I18N.API_USER_LOGIN, data, API_FUNCS.CALLBACK_LOGIN);
    }

    logout(e) {
        e.preventDefault();
        UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_LOGOUT);
        UI_FUNCS.SHOW(shareApp.sf.login);
        UI_FUNCS.HIDE(shareApp.sf.reminder);
        UI_FUNCS.CLOSE_SIDEBAR();
        shareApp.clearList();
        localStorage.removeItem(I18N.KEY_USER_ID);
        localStorage.removeItem(I18N.KEY_REQUEST_TOKEN);
        localStorage.removeItem(I18N.KEY_ALWAYS_LOGIN);
        shareApp.sf['login-nickname'].value = '';
        shareApp.sf['login-password'].value = '';
    }

    newReinder(e) {
        e.preventDefault();
        let item = new ReminderItem({});
        shareApp.reminders.push(item);
        shareApp.sf.reminder.ul.$.append(item.sf.$);
    }

    clearList() {
        shareApp.reinders.length = 0;
        shareApp.sf.reminder.ul.textContent = '';
    }
}
Object.freeze(ReminderApp);
class ReminderItem {
    constructor(o) {
        this[I18N.KEY_REMINDER_ID] = o[I18N.KEY_REMINDER_ID] || null;
        this[I18N.KEY_TITLE] = o[I18N.KEY_TITLE] || I18N.MSG_DEFAULT_REMINDER_TITLE();
        this[I18N.KEY_ATTACH_FILE] = o[I18N.KEY_ATTACH_FILE];
        this[I18N.KEY_COMPLETE_FLG] = o[I18N.KEY_COMPLETE_FLG];
        this[I18N.KEY_DEL_FLG] = false;

        this.sf = shareApp[I18N.KEY_REMINDER]();
        this.sf[`[name=${I18N.KEY_TITLE}]`].value = this[I18N.KEY_TITLE];
        this.sf[`[name=${I18N.KEY_TITLE}]`].onkeydown = ((item) => function (e) {
            if (e.keyCode == 13) {
                e.stopPropagation();
                item.update();
                return false;
            }
            return true;
        })(this);
        this.sf.img.$.style.maxHeight = `${window.innerHeight / 5}px`;
        if (this[I18N.KEY_ATTACH_FILE]) {
            this.sf[`button.no-img-btn`].$.remove();
            this.sf.img.src = this[I18N.KEY_ATTACH_FILE];
        } else {
            this.sf[`button.no-img-btn`].onclick = ((fileNode) => function (e) {
                e.preventDefault();
                fileNode.click();
            })(this.sf[`[name=${I18N.KEY_ATTACH_FILE}]`].$);
            this.sf[`[name=${I18N.KEY_ATTACH_FILE}]`].onchange = ((item) => function (e) {
                e.stopPropagation();
                if (e.target.files.length < 1)
                    return false;
                let file = e.target.files[0];
                if (!/image/.test(file.type)) {
                    UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_NOT_IMAGE);
                    return false;
                }
                item[I18N.KEY_ATTACH_FILE] = file;
                item.sf.img.src = URL.createObjectURL(file);
                item.update();
                UI_FUNCS.HIDE(item.sf[`button.no-img-btn`]);
                return false;
            })(this);
        }
        this.sf[`[name=${I18N.KEY_COMPLETE_FLG}]`].checked = Boolean(this[I18N.KEY_COMPLETE_FLG]);
        this.sf[`button.remove-btn`].onclick = ((item) => function (e) {
            e.preventDefault();
            item.remove();
        })(this);
        for (let element of this.sf.form.$.querySelectorAll('input,select,textarea')) {
            let f = SF.debounce(SF.observeForm(this.sf.form.$, ((item) => function (name, value, src) {
                item.update();
            })(this)), 300);
            element.addEventListener('change', f);
        }
    }

    update() {
        let formData = SF.formToObject(this.sf.form.$);
        let title = `${formData[I18N.KEY_TITLE]}`;
        if (title.replace(/\s/g, '').length < 1) {
            UI_FUNCS.SHOW_SNACKBAR(I18N.MSG_NO_TITLE);
            return;
        }
        let data = {};
        data[I18N.KEY_TITLE] = this[I18N.KEY_TITLE] = title;
        data[I18N.KEY_COMPLETE_FLG] = this[I18N.KEY_COMPLETE_FLG] = Boolean(formData[I18N.KEY_COMPLETE_FLG]);
        if (this[I18N.KEY_REMINDER_ID])
            data[I18N.KEY_REMINDER_ID] = this[I18N.KEY_REMINDER_ID];
        if (typeof (this[I18N.KEY_ATTACH_FILE]) != 'string')
            data[I18N.KEY_ATTACH_FILE] = this[I18N.KEY_ATTACH_FILE];
        API_FUNCS.REQUEST('POST', I18N.API_REMINDER_UPDATE, data, API_FUNCS.CALLBACK_REMINDER_REMOVE(this));
    }

    remove() {
        if (this[I18N.KEY_REMINDER_ID])
            API_FUNCS.REQUEST('POST', I18N.API_REMINDER_REMOVE, this[I18N.KEY_REMINDER_ID], API_FUNCS.CALLBACK_REMINDER_REMOVE(this));
        else {
            this.sf.$.remove();
            this[I18N.KEY_DEL_FLG] = true;
        }
    }
}
Object.freeze(ReminderItem);