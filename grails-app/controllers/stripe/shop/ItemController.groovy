package stripe.shop

import com.stripe.model.Product

class ItemController {

    def index() {
        [products: Product.list([active: true])]
    }

    def show(String id){
        [product: Product.retrieve(id)]
    }

}
