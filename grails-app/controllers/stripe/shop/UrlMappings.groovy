package stripe.shop

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        // ITEMS ROUTES
        "/"(controller: 'item', action: 'index')
        "/item/$name"(controller: 'item', action: 'show')

        // CART ROUTES
        "/cart"(controller: 'item', action: 'cart')
        "/cart/checkout"(controller: 'item', action: 'checkout')
        "/cart/checkout/ok"(controller: 'item', action: 'payed')

        // ERRORS VIEWS
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
