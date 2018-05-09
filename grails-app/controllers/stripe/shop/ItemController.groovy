package stripe.shop

import com.stripe.model.Account
import com.stripe.model.Customer
import com.stripe.model.Order
import com.stripe.model.Product
import com.stripe.model.SKU
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.util.Environment

class ItemController {

    GrailsApplication grailsApplication

    ArrayList<SKU> cartSkus
    List<Object> itemsParams
    Map<String, Object> orderParams
    Map<String, Object> orderPayParams
    Map<String, Object> customerParams

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
            cartSkus = ((SKU[]) session.getAttribute('skus'))
            cartSkus.add(SKU.retrieve(id))
            session.setAttribute('skus', cartSkus)
        } else {
            session.setAttribute('skus', [SKU.retrieve(id)])
        }
        redirect controller: 'item', action: 'show', id: Product.retrieve(SKU.retrieve(id).product).id, params: [name: Product.retrieve(SKU.retrieve(id).product).name]
    }

    @Transactional
    removeFromCart(String id) {
        cartSkus = ((SKU[]) session.getAttribute('skus'))
        for (SKU sku : cartSkus) {
            if (sku.id == id) {
                cartSkus.remove(sku)
                break
            }
        }
        session.setAttribute('skus', cartSkus)
        redirect controller: 'item', action: 'cart'
    }

    def checkout() {
        def publicKey
        if (Environment.currentEnvironment == Environment.DEVELOPMENT || Environment.currentEnvironment == Environment.TEST) {
            publicKey = grailsApplication.config.get('stripe.testing.public')
        } else {
            publicKey = grailsApplication.config.get('stripe.production.public')
        }
        itemsParams = new LinkedList<Object>()
        def skus
        (session.getAttribute('skus') as SKU[]).each {
            skus = new HashMap<Object, Object>()
            skus.put("type", "sku")
            skus.put("parent", it.id)
            itemsParams.add(skus)
        }
        orderParams = new HashMap<String, Object>()
        orderParams.put("currency", "eur")
        orderParams.put("items", itemsParams)
        [order: Order.create(orderParams), publicKey: publicKey]
    }

    @Transactional
    doPay(String id) {
        Order order = Order.retrieve(id)
        orderPayParams = new HashMap<String, Object>()
        orderPayParams.put("email", params.get('stripeEmail'))
        User buyingUser
        User userControl = User.findByEmail(params.get('stripeEmail') as String)
        if (userControl == null) {
            buyingUser = new User(email: params.get('stripeEmail'), password: ''.encodeAsMD5().toString(), stripeId: '1', accessLevel: 0)
            buyingUser.save(flush: true)
        } else {
            buyingUser = User.findByEmail(params.get('stripeEmail') as String)
            buyingUser.save(flush: true)
        }
        Customer customer
        if (buyingUser.stripeId == null || buyingUser.stripeId.length() <= 1) {
            customerParams = new HashMap<String, Object>()
            customerParams.put('email', order.email)
            customerParams.put('source', params.get('stripeToken'))
            customer = Customer.create(customerParams)
            buyingUser.stripeId = customer.id
            buyingUser.save(flush: true)
        } else {
            customer = Customer.retrieve(buyingUser.stripeId)
        }
        orderPayParams.put('customer', buyingUser.stripeId)
        orderPayParams.put("source", customer.defaultSource)
        order.pay(orderPayParams)
        session.removeAttribute('skus')
        redirect controller: 'item', action: 'payed', id: order.id
    }

    def paid(String id) {
        [order: Order.retrieve(id)]
    }

    @Transactional
    doSendPaidEmail(String id) {
        Order order = Order.retrieve(id)
        sendMail {
            from Account.retrieve().email
            subject order.id
            html view: '/mails/order', model: [order: order]
        }
        redirect controller: 'item', action: 'payed', id: order.id
    }

}
