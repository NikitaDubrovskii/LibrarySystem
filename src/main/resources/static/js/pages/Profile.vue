<template>
  <v-container class="my-5">
    <v-layout align-center justify-center row fill-height>
      <v-flex xs8 sm6 md4>
        <v-card class="text-xs-center pa-1">
          <v-responsive class="pt-4">
            <v-avatar size="100" color="grey lighten-2">
              <!--                            <v-img v-if="profile.picture" :src="profile.picture"></v-img>-->
            </v-avatar>
          </v-responsive>

          <v-card-text>
            <div class="subheading">{{ profile.firstName }} {{ profile.secondName }}</div>
            <div class="grey--text">{{ profile.age }}</div>
            <div class="grey--text">{{ profile.email }}</div>
            <div class="grey--text">{{ profile.phone }}</div>
            <div class="grey--text">{{ profile.locale }}</div>
            <div class="grey--text">{{ profile.creationDate }}</div>
          </v-card-text>

          <v-card-actions>
            <profile-edit-form :user="profile" :userAttr="editUser"/>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'
import ProfileEditForm from "../components/profile/ProfileEditForm.vue"

export default {
  components: {ProfileEditForm},

  computed: {
    ...mapState(['profile']),
  },

  methods: {
    editUser(user) {
      this.profile = user
    }
  }
}
</script>

<style scoped>

</style>