import Vue from 'vue'
import VueRouter from 'vue-router'
import UsersList from "pages/UsersList.vue"
import Auth from 'pages/Auth.vue'
import Profile from "pages/Profile.vue"
import BooksList from "pages/BooksList.vue"
import Hello from 'pages/Hello.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/users', component: UsersList },
    { path: '/books', component: BooksList },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    //{ path: '/hello', component: Hello },
    { path: '*', component: Profile },
]

export default new VueRouter({
    mode: 'history',

    routes
})