import Vue from 'vue'

const books = Vue.resource('/book{/id}')

export default {
    create: book => books.save({}, book),

    update: book => books.update({id: book.id}, book),

    remove: id => books.remove({id})
}