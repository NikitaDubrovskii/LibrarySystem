<template>
    <v-app class="grey lighten-4">
        <Navbar />
        <v-content class="mx-4 my-4">
            <router-view></router-view>
        </v-content>
    </v-app>


</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandlers } from "util/ws"
    import Navbar from "components/Navbar.vue";

    export default {
        components: { Navbar },

        computed: {
            ...mapState(['profile']),
        },

        methods: {
            ...mapMutations([
                'addUserMutation', 'updateUserMutation', 'removeUserMutation',
                'addBookMutation', 'updateBookMutation', 'removeBookMutation'
                ]),

            showUsers() {
                this.$router.push('/users')
            },

            showProfile() {
                this.$router.push('/user')
            },

            showBooks() {
                this.$router.push('/books')
            },
        },

        created() {
            addHandlers(data => {
                if (data.objectType === 'USER') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addUserMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateUserMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeUserMutation(data.body)
                            break
                        default:
                            console.error(`Looks like the eventType type is unknown "${data.eventType}"`)
                    }
                } else if (data.objectType === 'BOOK') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addBookMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateBookMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeBookMutation(data.body)
                            break
                        default:
                            console.error(`Looks like the eventType type is unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the objectType type is unknown "${data.objectType}"`)
                }
            })
        },

        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        },
    }
</script>

<style>

</style>