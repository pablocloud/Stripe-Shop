package stripe.shop

import com.stripe.model.Order
import com.stripe.model.Product
import com.stripe.model.SKU
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.util.Environment

class ItemController {

    GrailsApplication grailsApplication

    def index() {
        [products: Product.list([active: true])]
    }

    def show(String id) {
        [product: Product.retrieve(id)]
    }

    def cart() {
        if (session.getAttribute('skus') == null) {
            [items: [:]]
        } else {
            [items: session.getAttribute('skus') as SKU[]]
        }
    }

    @Transactional
    addToCart(String id) {
        if (session.getAttribute('skus') != null) {
            ((SKU[]) session.getAttribute('skus')).collect().add(SKU.retrieve(id))
        } else {
            session.setAttribute('skus', [SKU.retrieve(id)])
        }
        redirect controller: 'item', action: 'index'
    }

    def checkout() {
        def publicKey
        if (Environment.currentEnvironment == Environment.DEVELOPMENT || Environment.currentEnvironment == Environment.TEST) {
            publicKey = grailsApplication.config.get('stripe.testing.public')
        } else {
            publicKey = grailsApplication.config.get('stripe.production.public')
        }
        Map<String, Object> orderParams = new HashMap<String, Object>()
        orderParams.put("currency", "eur")
        orderParams.put("items", [["type": "sku", "parent": (session.getAttribute('skus') as SKU[]).first().id]])
        [order: Order.create(orderParams), publicKey: publicKey]
    }

    @Transactional
    doPay(String id) {
        Order order = Order.retrieve(id)
        Map<String, Object> orderPayParams = new HashMap<String, Object>()
        orderPayParams.put("email", params.get('stripeEmail'))
        orderPayParams.put("source", params.get('stripeToken'))
        order.pay(orderPayParams)
        redirect controller: 'item', action: 'payed', id: order.id
    }

    def payed(String id) {
        [order: Order.retrieve(id)]
    }

}
