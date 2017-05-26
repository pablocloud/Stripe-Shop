package stripe.shop

import com.stripe.Stripe
import grails.core.GrailsApplication
import grails.util.Environment

class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->
        if (Environment.currentEnvironment == Environment.DEVELOPMENT || Environment.currentEnvironment == Environment.TEST) {
            Stripe.apiKey = grailsApplication.config.get('stripe.testing.private')
        } else {
            Stripe.apiKey = grailsApplication.config.get('stripe.production.private')
        }
    }

    def destroy = {

    }

}
