package stripe.shop

import com.stripe.model.Charge
import com.stripe.model.ChargeCollection
import com.stripe.model.Customer
import grails.gorm.transactions.Transactional

class UserController {

    Map<String, Object> stripeParams

    def login() {

    }

    @Transactional
    doLogin(User user) {
        if (User.findByEmailAndPassword(user.email, user.password.encodeAsMD5().toString()) != null) {
            user = User.findByEmailAndPassword(user.email, user.password.encodeAsMD5().toString())
            session.setAttribute('user', user)
            redirect controller: 'user', action: 'panel', id: user.id
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
        Customer customer = Customer.retrieve(user.stripeId)
        stripeParams = new HashMap<String, Object>()
        stripeParams.put('customer', user.stripeId)
        ChargeCollection charges = Charge.list(stripeParams)
        [customer: customer, user: user, charges: charges.data]
    }

}
