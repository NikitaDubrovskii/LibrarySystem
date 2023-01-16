<template>
  <div>
    <h1 class="subheading grey--text">Users</h1>
    <users-form class="right"/>

    <v-container class="my-5">
      <users-row v-for="user in sortedUsers"
                 :key="user.id"
                 :user="user"
                 :editUser="editUser"
                 class="mb-1 elevation-1"/>
    </v-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import UsersRow from "components/users/UsersRow.vue"
import UsersForm from "components/users/UsersForm.vue"

export default {
  components: {
    UsersRow,
    UsersForm
  },

  data() {
    return {
      user: null
    }
  },

  computed: {
    ...mapGetters(['sortedUsers']),
  },

  methods: {
    editUser(user) {
      this.user = user
    },
  }
}
</script>

<style scoped>

</style>