import Vue from 'vue'
import Vuex from 'vuex'
import usersApi from "api/users"
import booksApi from 'api/books'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        users: frontendData.users,
        profile: frontendData.profile,
        books: frontendData.books,
    },

    getters: {
        sortedUsers: state => (state.users || []).sort((a, b) => (a.id - b.id)),

        sortedBooks: state => (state.books || []).sort((a, b) => (a.id - b.id))
    },

    mutations: {
        addUserMutation(state, user) {
            state.users = [
                ...state.users,
                user
            ]
        },

        addBookMutation(state, book) {
            state.books = [
                ...state.books,
                book
            ]
        },

        updateUserMutation(state, user) {
            const updateIndex = state.users.findIndex(item => item.id === user.id)

            state.users = [
                ...state.users.slice(0, updateIndex),
                user,
                ...state.users.slice(updateIndex + 1)
            ]
        },

        updateBookMutation(state, book) {
            const updateIndex = state.books.findIndex(item => item.id === book.id)

            state.books = [
                ...state.books.slice(0, updateIndex),
                book,
                ...state.books.slice(updateIndex + 1)
            ]
        },

        removeUserMutation(state, user) {
            const removeIndex = state.users.findIndex(item => item.id === user.id)

            if (removeIndex > -1) {
                state.users = [
                    ...state.users.slice(0, removeIndex),
                    ...state.users.slice(removeIndex + 1)
                ]
            }
        },

        removeBookMutation(state, book) {
            const removeIndex = state.books.findIndex(item => item.id === book.id)

            if (removeIndex > -1) {
                state.books = [
                    ...state.books.slice(0, removeIndex),
                    ...state.books.slice(removeIndex + 1)
                ]
            }
        },
    },

    actions: {
        async addUserAction({commit, state}, user) {
            const result = await usersApi.create(user)
            const data = await result.json()
            const index = state.users.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updateUserMutation', data)
            } else {
                commit('addUserMutation', data)
            }
        },

        async addBookAction({commit, state}, book) {
            const result = await booksApi.create(book)
            const data = await result.json()
            const index = state.books.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updateBookMutation', data)
            } else {
                commit('addBookMutation', data)
            }
        },

        async updateUserAction({commit}, user) {
            const result = await usersApi.update(user)
            const data = await result.json()

            commit('updateUserMutation', data)
        },

        async updateBookAction({commit}, book) {
            const result = await booksApi.update(book)
            const data = await result.json()

            commit('updateBookMutation', data)
        },

        async removeUserAction({commit}, user) {
            const result = await usersApi.remove(user.id)

            if (result.ok) {
                commit('removeUserMutation', user)
            }
        },

        async removeBookAction({commit}, book) {
            const result = await booksApi.remove(book.id)

            if (result.ok) {
                commit('removeBookMutation', book)
            }
        },
    }
})