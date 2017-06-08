package stripe.shop

import grails.gorm.transactions.Transactional

class UserController {

    def login() {

    }

    @Transactional
    doLogin(User user) {
        if (User.findByEmailAndPassword(user.email, user.password.encodeAsMD5().toString()) != null) {
            user = User.findByEmailAndPassword(user.email, user.password.encodeAsMD5().toString())
            session.setAttribute('user', user)
            redirect controller: 'user', action: 'panel'
        } else {
            redirect controller: 'user', action: 'login'
        }
    }

    def register() {

    }

    @Transactional
    doRegister(User user) {

    }

    def panel(User user) {
        [user: user]
    }

}
