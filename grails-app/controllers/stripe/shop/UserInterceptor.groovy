package stripe.shop


class UserInterceptor {

    boolean before() {
        if (session.getAttribute('user') == null && actionName == 'panel') {
            return redirect(controller: 'user', action: 'login')
        } else if (session.getAttribute('user') != null && (actionName == 'login' || actionName == 'register')) {
            return redirect(controller: 'user', action: 'panel', id: ((User) session.getAttribute('user')).id)
        } else {
            true
        }
    }

    boolean after() {
        true
    }

    void afterView() {

    }

}
