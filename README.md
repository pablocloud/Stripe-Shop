# Stripe-Shop (Grails APP)

### Shop system using Stripe as backend

All the data is stored in Stripe using Stripe's API, no data is stored in
the Grails-App.

##### Actual features : 

- Base app, full request/full response
- Base products listing
- Base cart
- Base checkout
- Base payment and payed order display

##### TODO :

- Order status check
- User panel
- Pretty design
- Make the web Async

##### Configuration :

You need to setup your data in application.yml

Keys : 

    stripe:
        testing:
            public:
            private:
        production:
            public:
            private:
            
Email settings :

    mail:
        host:
        port:
        username:
        password: