import Vue from 'vue'

const users = Vue.resource('/user{/id}')

export default {
    create: user => users.save({}, user),

    update: user => users.update({id: user.id}, user),

    remove: id => users.remove({id})
}