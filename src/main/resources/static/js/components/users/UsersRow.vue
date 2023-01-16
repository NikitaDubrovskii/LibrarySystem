<template>
  <div>
    <v-card flat class="pa-3">
      <v-layout row wrap>
        <v-flex xs0.5 sm0.5 md0.5>
          <div class="caption grey--text">Id</div>
          <div>{{ user.id }}</div>
        </v-flex>

        <v-flex xs6 sm3 md3>
          <div class="caption grey--text">Name</div>
          <div>{{ user.firstName }} {{ user.secondName }}</div>
        </v-flex>

        <v-flex xs1 sm1 md1>
          <div class="caption grey--text">Age</div>
          <div>{{ user.age }}</div>
        </v-flex>

        <v-flex xs8 sm6 md3>
          <div class="caption grey--text">Email</div>
          <div>{{ user.email }}</div>
        </v-flex>

        <v-flex xs8 md2>
          <div class="caption grey--text">Phone number</div>
          <div>{{ user.phone }}</div>
        </v-flex>

        <v-spacer></v-spacer>

        <v-flex xs12 sm12 md2>
          <div class="right">
            <user-info :user="user"/>
            <users-edit-form :user="user" :userAttr="editUser" @click="edit"/>
            <v-btn small icon @click="del">
              <v-icon>delete</v-icon>
            </v-btn>
          </div>
        </v-flex>
      </v-layout>
    </v-card>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
import UsersEditForm from "./UsersEditForm.vue";
import UserInfo from "./UserInfo.vue";

export default {
  components: {UserInfo, UsersEditForm},

  props: ['user', 'editUser'],

  methods: {
    ...mapActions(['removeUserAction']),

    edit() {
      this.editUser(this.user)
    },

    del() {
      this.removeUserAction(this.user)
    }
  },

  data: () => ({
    show: false
  })
}
</script>

<style scoped>

</style>